package lokeshsaini.mybooklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> bookList = new ArrayList<>();
    ListView list;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            bookList = savedInstanceState.getStringArrayList("headerList");
        }

        list = (ListView) findViewById(R.id.list);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        list.setAdapter(arrayAdapter);

        Button searchButton = (Button) findViewById(R.id.search_btn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm =
                        (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    EditText input = (EditText) findViewById(R.id.query_et);
                    String inputQuery = input.getText().toString();
                    inputQuery = inputQuery.replace(" ", "+");
                    String urlString = "https://www.googleapis.com/books/v1/volumes?q=" + inputQuery + "&orderBy=newest&maxResults=20";
                    new ProcessJSON().execute(urlString);
                } else {
                    Toast.makeText(MainActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("headerList", bookList);
        super.onSaveInstanceState(outState);
    }

    private class ProcessJSON extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Fetching Data...", Toast.LENGTH_LONG).show();
        }

        protected String doInBackground(String... strings) {
            String stream;
            String urlString = strings[0];

            HTTPDataHandler httpDataHandler = new HTTPDataHandler();
            stream = httpDataHandler.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream) {
            list = (ListView) findViewById(R.id.list);
            bookList.clear();
            if (stream != null) {
                try {
                    JSONObject reader = new JSONObject(stream);
                    if (reader.getInt("totalItems") == 0) {
                        Toast.makeText(MainActivity.this, "No Results Found", Toast.LENGTH_SHORT).show();
                    } else {
                        JSONArray bookArray = reader.getJSONArray("items");

                        for (int i = 0; i < bookArray.length(); i++) {
                            JSONObject BookObject = bookArray.getJSONObject(i);
                            String title, authors;
                            JSONObject BookDetails = BookObject.getJSONObject("volumeInfo");

                            title = BookDetails.getString("title");

                            if (BookDetails.has("authors")) {
                                authors = " By ";
                                authors += (BookDetails.getString("authors"));
                                authors = authors.replace("[", "");
                                authors = authors.replace("]", "");
                                authors = authors.replace("\"", "");
                                authors = authors.replace(",", ", ");
                            } else {
                                authors = "Unknown";
                            }

                            String s = title + authors;

                            bookList.add(s);

                            list.setAdapter(arrayAdapter);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
