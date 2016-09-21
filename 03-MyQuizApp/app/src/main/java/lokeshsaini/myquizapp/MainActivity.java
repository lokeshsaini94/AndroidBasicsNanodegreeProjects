package lokeshsaini.myquizapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int q2 = 0;
    int q5 = 0;

    TextView q2Yes;
    TextView q2No;
    ImageView q5A1;
    ImageView q5A2;
    ImageView q5A3;
    ImageView q5A4;
    TextView resultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        q2Yes = (TextView) findViewById(R.id.q2_yes);
        q2No = (TextView) findViewById(R.id.q2_no);
        q5A1 = (ImageView) findViewById(R.id.q5_1);
        q5A2 = (ImageView) findViewById(R.id.q5_2);
        q5A3 = (ImageView) findViewById(R.id.q5_3);
        q5A4 = (ImageView) findViewById(R.id.q5_4);
        resultsTextView = (TextView) findViewById(R.id.results);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultsTextView.setText(generatedResults());
                Toast.makeText(MainActivity.this, generatedResults(), Toast.LENGTH_LONG).show();
            }
        });

        q2Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q2 = 1;
                q2Yes.setTextColor(getColor(R.color.black));
                q2No.setTextColor(getColor(R.color.gray));
            }
        });

        q2No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q2 = 2;
                q2Yes.setTextColor(getColor(R.color.gray));
                q2No.setTextColor(getColor(R.color.black));
            }
        });

        q5A1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q5 = 1;
                q5A1.setColorFilter(getColor(R.color.black));
                q5A2.setColorFilter(getColor(R.color.gray));
                q5A3.setColorFilter(getColor(R.color.gray));
                q5A4.setColorFilter(getColor(R.color.gray));
            }
        });

        q5A2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q5 = 2;
                q5A1.setColorFilter(getColor(R.color.gray));
                q5A2.setColorFilter(getColor(R.color.black));
                q5A3.setColorFilter(getColor(R.color.gray));
                q5A4.setColorFilter(getColor(R.color.gray));
            }
        });

        q5A3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q5 = 3;
                q5A1.setColorFilter(getColor(R.color.gray));
                q5A2.setColorFilter(getColor(R.color.gray));
                q5A3.setColorFilter(getColor(R.color.black));
                q5A4.setColorFilter(getColor(R.color.gray));
            }
        });

        q5A4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q5 = 4;
                q5A1.setColorFilter(getColor(R.color.gray));
                q5A2.setColorFilter(getColor(R.color.gray));
                q5A3.setColorFilter(getColor(R.color.gray));
                q5A4.setColorFilter(getColor(R.color.black));
            }
        });
    }

    // Returns String with Results
    private String generatedResults() {
        String q1Answer;
        String q2Answer;
        String q3Answer;
        String q4Answer;
        String q5Answer;
        RadioButton q3A16 = (RadioButton) findViewById(R.id.q3_16);
        CheckBox q4A2 = (CheckBox) findViewById(R.id.q4_2);
        CheckBox q4A9 = (CheckBox) findViewById(R.id.q4_9);
        CheckBox q4A5 = (CheckBox) findViewById(R.id.q4_5);
        EditText q1Trump = (EditText) findViewById(R.id.q1_trump);
        String answer = q1Trump.getText().toString();
        if (answer.equalsIgnoreCase("yes")) {
            q1Answer = getString(R.string.correct);
        } else {
            q1Answer = getString(R.string.wrong);
        }
        if (q2 == 1) {
            q2Answer = getString(R.string.correct);
        } else {
            q2Answer = getString(R.string.wrong);
        }
        if (q3A16.isChecked()) {
            q3Answer = getString(R.string.correct);
        } else {
            q3Answer = getString(R.string.wrong);
        }
        if (!q4A2.isChecked() && q4A9.isChecked() && q4A5.isChecked()) {
            q4Answer = getString(R.string.correct);
        } else {
            q4Answer = getString(R.string.wrong);
        }
        if (q5 == 2) {
            q5Answer = getString(R.string.correct);
        } else {
            q5Answer = getString(R.string.wrong);
        }

        String result = "";
        result += getString(R.string.question1) + q1Answer;
        result += getString(R.string.question2) + q2Answer;
        result += getString(R.string.question3) + q3Answer;
        result += getString(R.string.question4) + q4Answer;
        result += getString(R.string.question5) + q5Answer;

        return result;
    }
}
