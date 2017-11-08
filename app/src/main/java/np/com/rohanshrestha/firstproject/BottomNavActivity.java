package np.com.rohanshrestha.firstproject;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class BottomNavActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {

            Toast.makeText(BottomNavActivity.this, "Home", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.nav_library) {

            Toast.makeText(BottomNavActivity.this, "library", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.nav_office) {
            Toast.makeText(BottomNavActivity.this, "office", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
