package lokeshsaini.reportcard;

/**
 * Created by lokeshsaini94 on 24-09-2016.
 */

public class ReportCard {
    private String mStudentName;
    private String mSubjectName;
    private int mSubjectIcon;
    private int mMarks;

    public ReportCard(int icon, String mSubjectName, int mMarks) {
        this.mSubjectName = mSubjectName;
        this.mSubjectIcon = icon;
        this.mMarks = mMarks;

    }

    public void setmStudentName(String mStudentName) {
        this.mStudentName = mStudentName;
    }

    public String getmSubjectName() {
        return mSubjectName;
    }

    public int getmMarks() {
        return mMarks;
    }

    public void setmMarks(int mMarks) {
        this.mMarks = mMarks;
    }

    public int getmSubjectIcon() {
        return mSubjectIcon;
    }

    public char getGrade(int marks) {
        if (marks > 90 && marks <= 100) {
            return 'O';
        } else if (marks > 80 && marks <= 90) {
            return 'A';
        } else if (marks > 65 && marks <= 80) {
            return 'B';
        } else if (marks > 50 && marks <= 65) {
            return 'C';
        } else if (marks > 35 && marks <= 50) {
            return 'D';
        } else if (marks < 35) {
            return 'F';
        }
        return 'x';
    }

    @Override
    public String toString() {
        return "Student " + mStudentName + " Received " + getGrade(this.mMarks) + " in " + mSubjectName;
    }
}