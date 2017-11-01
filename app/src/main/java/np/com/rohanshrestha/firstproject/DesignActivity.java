package np.com.rohanshrestha.firstproject;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tv_first = (TextView) findViewById(R.id.tv_first);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(tv_first, "Snackbar", Snackbar.LENGTH_INDEFINITE).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("legend", "Undo clicked");
                    }
                }).show();
            }
        });
    }
}
