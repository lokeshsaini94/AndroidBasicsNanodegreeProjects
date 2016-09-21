package lokeshsaini.mymusicalstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        final String[] names = {"Laung Gawacha", "Bass Rani", "Jungle Raja", "Aaja", "Chennai Bass", "Heer (Dirty Dewarist Remix)", "Mumbai Dance", "Fuck Nucleya"};

        ListAdapter adapter = new MyAdapter(this, names);
        ListView mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SongsActivity.this, names[i] + " Song selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SongsActivity.this, PlayerActivity.class);
                startActivity(intent);
            }
        });
    }
}