package np.com.rohanshrestha.firstproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NInthActivity extends AppCompatActivity {

    private EditText et_name;
    private TextView tv_result;
    private SharedPreferences preferences;

    public static final String PREF_NAME = "test_pref";
    private final String KEy_NAME = "name";
    private final String KEY_AGE = "age";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth);
        preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        et_name = (EditText) findViewById(R.id.et_name);
        tv_result = (TextView) findViewById(R.id.tv_result);
    }

    public void save(View view) {
        String name = et_name.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEy_NAME, name);
        editor.putInt(KEY_AGE, 25);
        editor.commit();
    }

    public void display(View view) {
        String result = preferences.getString(KEy_NAME, "Heloo");
        int age = preferences.getInt(KEY_AGE, 0);
        tv_result.setText(result);
    }
}
