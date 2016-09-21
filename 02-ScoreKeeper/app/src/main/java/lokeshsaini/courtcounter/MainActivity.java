package lokeshsaini.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declaring Variables for score
    int teamAScore = 0;
    int teamBScore = 0;
    int teamAOut = 0;
    int teamBOut = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Adds 6 to team A's score
    public void teamA6(View view) {
        if (teamAOut < 10) {
            teamAScore = teamAScore + 6;
        }
        displayA();
    }

    // Adds 4 to team A's score
    public void teamA4(View view) {
        if (teamAOut < 10) {
            teamAScore = teamAScore + 4;
        }
        displayA();
    }

    // Adds 3 to team A's score
    public void teamA3(View view) {
        if (teamAOut < 10) {
            teamAScore = teamAScore + 3;
        }
        displayA();
    }

    // Adds 2 to team A's score
    public void teamA2(View view) {
        if (teamAOut < 10) {
            teamAScore = teamAScore + 2;
        }
        displayA();
    }

    // Adds 1 to team A's score
    public void teamA1(View view) {
        if (teamAOut < 10) {
            teamAScore = teamAScore + 1;
        }
        displayA();
    }

    // Adds 6 to team B's score
    public void teamB6(View view) {
        if (teamBOut < 10) {
            teamBScore = teamBScore + 6;
        }
        displayB();
    }

    // Adds 4 to team B's score
    public void teamB4(View view) {
        if (teamBOut < 10) {
            teamBScore = teamBScore + 4;
        }
        displayB();
    }

    // Adds 3 to team B's score
    public void teamB3(View view) {
        if (teamBOut < 10) {
            teamBScore = teamBScore + 3;
        }
        displayB();
    }

    // Adds 2 to team B's score
    public void teamB2(View view) {
        if (teamBOut < 10) {
            teamBScore = teamBScore + 2;
        }
        displayB();
    }

    // Adds 1 to team B's score
    public void teamB1(View view) {
        if (teamBOut < 10) {
            teamBScore = teamBScore + 1;
        }
        displayB();
    }

    // Adds 1 to team A's player out
    public void teamAOut(View view) {
        if (teamAOut < 10) {
            teamAOut = teamAOut + 1;
        }
        displayA();
    }

    // Adds 1 to team B's player out
    public void teamBOut(View view) {
        if (teamBOut < 10) {
            teamBOut = teamBOut + 1;
        }
        displayB();
    }

    // Resets the score
    public void resetScore(View view) {
        teamAScore = 0;
        teamBScore = 0;
        teamAOut = 0;
        teamBOut = 0;
        displayA();
        displayB();
    }

    // Sets and Displays the score for team A
    private void displayA() {
        TextView aTextView = (TextView) findViewById(R.id.team_a_score);
        aTextView.setText("" + teamAScore);
        TextView aOutTextView = (TextView) findViewById(R.id.team_a_out_TV);
        aOutTextView.setText("" + teamAOut);
    }

    // Sets and Displays the score for team B
    private void displayB() {
        TextView bTextView = (TextView) findViewById(R.id.team_b_score);
        bTextView.setText("" + teamBScore);
        TextView bOutTextView = (TextView) findViewById(R.id.team_b_out_TV);
        bOutTextView.setText("" + teamBOut);
    }
}
