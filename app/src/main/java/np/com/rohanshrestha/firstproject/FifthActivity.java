package np.com.rohanshrestha.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FifthActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        FirstFragment frag = new FirstFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_content, frag).commit();
    }

    @Override
    public void onClick(String s) {
        SecondFragment frag2 = SecondFragment.newInstance(s);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, frag2, "Second").commit();

    }
}
