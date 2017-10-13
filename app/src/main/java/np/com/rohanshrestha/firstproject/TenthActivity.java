package np.com.rohanshrestha.firstproject;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TenthActivity extends AppCompatActivity {

    private DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenth);
        handler = new DatabaseHandler(this);

        final EditText et_name = (EditText) findViewById(R.id.et_name);
        final EditText et_roll = (EditText) findViewById(R.id.et_roll);
        final TextView tv_result = (TextView) findViewById(R.id.tv_result);
        Button btn_save = (Button) findViewById(R.id.btn_save);
        Button btn_update = (Button) findViewById(R.id.btn_update);
        Button btn_delete = (Button) findViewById(R.id.btn_delete);
        Button btn_display = (Button) findViewById(R.id.btn_display);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                int roll = Integer.parseInt(et_roll.getText().toString());

                long a = handler.addStudent(name, roll);
                if (a != -1) {
                    Toast.makeText(TenthActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                handler.updateStudent("Ram", name);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.deleteStudent();
            }
        });

        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = handler.getStudent();
                tv_result.setText(name);
            }
        });
    }
}
