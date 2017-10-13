package np.com.rohanshrestha.firstproject.models;

/**
 * Created by rohan on 9/21/17.
 */

public class Student {
    private String name;
    private int roll;
    private String grade;

    public Student() {
    }

    public Student(String name, int roll, String grade) {
        this.name = name;
        this.roll = roll;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
