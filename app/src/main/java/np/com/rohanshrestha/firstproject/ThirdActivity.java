package np.com.rohanshrestha.firstproject;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        fm = getSupportFragmentManager();
        FirstFragment frag1 = new FirstFragment();

        fm.beginTransaction().add(R.id.main_content, frag1).commit();

        Button btn_first = (Button) findViewById(R.id.btn_first);
        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragment frag2 = new SecondFragment();
                fm.beginTransaction().replace(R.id.main_content, frag2).addToBackStack(null).commit();
            }
        });

    }
}
