package lokeshsaini.mymusicalstructure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PlayerActivity extends AppCompatActivity {

    ImageView play;
    ImageView next;
    ImageView previous;
    boolean playClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        play = (ImageView) findViewById(R.id.play);
        next = (ImageView) findViewById(R.id.next);
        previous = (ImageView) findViewById(R.id.previous);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playClicked) {
                    play.setImageResource(R.drawable.ic_play_circle_filled_white_24dp);
                    playClicked = false;
                    Toast.makeText(PlayerActivity.this, "Play", Toast.LENGTH_SHORT).show();
                } else {
                    play.setImageResource(R.drawable.ic_pause_circle_filled_white_24dp);
                    playClicked = true;
                    Toast.makeText(PlayerActivity.this, "Pause", Toast.LENGTH_SHORT).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PlayerActivity.this, "Next song", Toast.LENGTH_SHORT).show();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PlayerActivity.this, "Previous song", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
