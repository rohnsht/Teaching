package np.com.rohanshrestha.firstproject.dummy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import np.com.rohanshrestha.firstproject.DatePickerFragment;
import np.com.rohanshrestha.firstproject.R;

public class SecondActivity extends AppCompatActivity implements DatePickerFragment.OnDateSelectedListener {

    //private TextView tv_first, tv_second;
    private AppCompatSpinner spinner;
    private ArrayList<String> superHeroList;
    private Button btn_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SwitchCompat btn_switch = (SwitchCompat) findViewById(R.id.btn_switch);
        ToggleButton btn = (ToggleButton) findViewById(R.id.btn_toggle);
        btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SecondActivity.this, "Button is on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SecondActivity.this, "Button is off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SecondActivity.this, "Button is on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SecondActivity.this, "Button is off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        superHeroList = new ArrayList<String>();
        superHeroList.add("Batman");
        superHeroList.add("Superman");
        superHeroList.add("Ironman");
        superHeroList.add("Spiderman");

        spinner = (AppCompatSpinner) findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                superHeroList);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String result = superHeroList.get(position);
                Toast.makeText(SecondActivity.this,
                        result,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_date = (Button) findViewById(R.id.date_picker);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment frag = new DatePickerFragment();
                frag.show(getSupportFragmentManager(), "DatePicker");
            }
        });

        ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(this)
                .load("http://lorempixel.com/400/200/sports/")
                .resize(200, 200)
                .placeholder(R.drawable.ic_adb)
                .error(R.drawable.ic_rowing)
                .into(image);

        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.profile_image);
        Picasso.with(this).load("http://lorempixel.com/400/200/").resize(100, 100).centerCrop().into(circleImageView);
    }

    public void btnClicked(View view) {
        int pos = spinner.getSelectedItemPosition();
        String result = superHeroList.get(pos);
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDateSet(int year, int month, int day) {
        btn_date.setText(year + "/" + month + "/" + day);
    }
}
