package np.com.rohanshrestha.firstproject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import np.com.rohanshrestha.firstproject.models.Flower;

public class AsyncActivity extends AppCompatActivity {

    private TextView tv_result;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        String url = "http://services.hanselandpetal.com/feeds/flowers.json";

        tv_result = (TextView) findViewById(R.id.tv_result);

        new CustomTask().execute(url);

    }

    public void postClicked(View view) {
        String name = "Ram";
        String password = "123456";
        String url = "https://postman-echo.com/post";

        new PostTask().execute(url, name, password);

    }


    public void showProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();
    }

    public void hideProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    private class CustomTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            String res = null;
            try {
                URL url = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                res = sb.toString();


            } catch (Exception e) {
                e.printStackTrace();
            }


            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            hideProgressDialog();


            //ArrayList<Flower> flowers = JsonParser.flowerParser(result);
            Gson gson = new Gson();

            ArrayList<Flower> flowerList = gson.fromJson(result, new TypeToken<ArrayList<Flower>>() {
            }.getType());

            for (Flower flower : flowerList) {
                Log.d("legend", flower.getName());
            }
        }
    }

    private class PostTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            String name = params[1];
            String pass = params[2];
            String res = null;

            String postParams = "name=" + name + "&password=" + pass;

            try {
                URL url = new URL(urlString);

                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(10000);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                os.write(postParams.getBytes());
                os.flush();
                os.close();

                conn.connect();

                // Read response
                InputStream inputStream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                res = sb.toString();


            } catch (Exception e) {
                e.printStackTrace();
            }


            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            hideProgressDialog();
            Toast.makeText(AsyncActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
