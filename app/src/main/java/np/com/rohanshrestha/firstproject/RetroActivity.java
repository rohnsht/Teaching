package np.com.rohanshrestha.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import np.com.rohanshrestha.firstproject.models.Flower;
import np.com.rohanshrestha.firstproject.rest.ApiClient;
import np.com.rohanshrestha.firstproject.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro);

        Retrofit retrofit = ApiClient.getClient();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        // visible
        Call<ArrayList<Flower>> call = apiInterface.getFlowers();
        call.enqueue(new Callback<ArrayList<Flower>>() {
            @Override
            public void onResponse(Call<ArrayList<Flower>> call, Response<ArrayList<Flower>> response) {
                // invisible
                if (response.isSuccessful()) {
                    ArrayList<Flower> flowerArrayList = response.body();

                    for (Flower flower : flowerArrayList) {
                        Log.d("legend", flower.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Flower>> call, Throwable t) {
                // invisible
                Toast.makeText(RetroActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
