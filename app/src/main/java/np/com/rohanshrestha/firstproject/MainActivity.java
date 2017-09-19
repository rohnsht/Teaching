package np.com.rohanshrestha.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import np.com.rohanshrestha.firstproject.dummy.SecondActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etFirst;
    private Button btnFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("legend", "oncreate");

        etFirst = (EditText) findViewById(R.id.et_first);
        btnFirst = (Button) findViewById(R.id.btn_first);

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = etFirst.getText().toString();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Data", s);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("legend", "onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("legend", "onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("legend", "onpause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("legend", "onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("legend", "ondestroy");
    }
}
