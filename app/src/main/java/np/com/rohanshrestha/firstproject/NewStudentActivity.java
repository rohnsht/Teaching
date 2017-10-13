package np.com.rohanshrestha.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import np.com.rohanshrestha.firstproject.models.Student;

public class NewStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        final DatabaseHandler handler = new DatabaseHandler(this);

        final EditText et_name = (EditText) findViewById(R.id.et_name);
        final EditText et_roll = (EditText) findViewById(R.id.et_roll);
        final EditText et_class = (EditText) findViewById(R.id.et_class);
        Button btn_save = (Button) findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                int roll = Integer.parseInt(et_roll.getText().toString());
                String grade = et_class.getText().toString();

                Student student = new Student(name, roll, grade);
                long a = handler.addStudent(student);
                if (a != -1) {
                    Toast.makeText(NewStudentActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
    }
}
