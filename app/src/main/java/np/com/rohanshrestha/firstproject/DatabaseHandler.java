package np.com.rohanshrestha.firstproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import np.com.rohanshrestha.firstproject.models.Student;

/**
 * Created by rohan on 9/17/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "test";
    private static final int DATABASE_VERSION = 1;

    private final String STUDENT_TABLE = "student";
    private final String KEY_ID = "id";
    private final String KEY_NAME = "name";
    private final String KEY_ROLL = "roll";
    private final String KEY_CLASS = "class";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_student_table = "CREATE TABLE " + STUDENT_TABLE + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " VARCHAR, " + KEY_ROLL + " INTEGER," + KEY_CLASS + " VARCHAR )";

        db.execSQL(create_student_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropQuery = "DROP TABLE IF EXISTS " + STUDENT_TABLE;
        db.execSQL(dropQuery);

        onCreate(db);
    }

    public long addStudent(String name, int roll) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_ROLL, roll);

        long r = db.insert(STUDENT_TABLE, null, values);

        db.close();

        return r;
    }

    public long addStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_ROLL, student.getRoll());
        values.put(KEY_CLASS, student.getGrade());

        long r = db.insert(STUDENT_TABLE, null, values);

        db.close();

        return r;
    }

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "Select * from " + STUDENT_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student();
                    student.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    student.setRoll(cursor.getInt(cursor.getColumnIndex(KEY_ROLL)));
                    student.setGrade(cursor.getString(cursor.getColumnIndex(KEY_CLASS)));

                    students.add(student);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return students;
    }

    public String getStudent() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "Select * from " + STUDENT_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String name = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                int roll = cursor.getInt(cursor.getColumnIndex(KEY_ROLL));
            }
        }

        cursor.close();
        db.close();

        return name;
    }

    public void updateStudent(String oldName, String newName) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newName);

        db.update(STUDENT_TABLE, values, KEY_NAME + " = ?", new String[]{oldName});
        db.close();

    }

    public void deleteStudent() {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(STUDENT_TABLE, null, null);
        //db.delete(STUDENT_TABLE, KEY_NAME + " = ?", new String[]{name});
        db.close();
    }
}
