package np.com.rohanshrestha.firstproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        fm = getSupportFragmentManager();

        FirstFragment frag1 = new FirstFragment();
        fm.beginTransaction().add(R.id.first_content, frag1, "FirstFragment").commit();

        SecondFragment frag2 = new SecondFragment();
        fm.beginTransaction().add(R.id.second_content, frag2, "SecondFragment").commit();
    }

    @Override
    public void onClick(String s) {
        SecondFragment frag = (SecondFragment) fm.findFragmentByTag("SecondFragment");
        frag.setData(s);
    }

}
