package lokeshsaini.myinventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListViewAdapter customAdapter;
    ListView listView;
    DBHandler db;
    ArrayList<Product> listArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DBHandler(this);

        listView = (ListView) findViewById(R.id.listView);
        TextView empty = (TextView) findViewById(R.id.empty);

        listArray = db.getProducts();
        if (listArray.size() == 0) {
            empty.setText(R.string.add_product);
        } else {
            empty.setText("");
        }

        customAdapter = new ListViewAdapter(listArray);
        customAdapter.notifyDataSetChanged();

        listView.setAdapter(customAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                intent.putExtra("HEADER", "Add a New Item");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listArray.clear();
        listArray = db.getProducts();
        customAdapter = new ListViewAdapter(listArray);
        listView.setAdapter(customAdapter);
    }
}
