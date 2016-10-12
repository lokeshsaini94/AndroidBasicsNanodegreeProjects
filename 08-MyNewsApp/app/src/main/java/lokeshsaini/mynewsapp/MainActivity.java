package lokeshsaini.mynewsapp;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public TextView emptyView;
    public String urlFinal;
    ProgressDialog progDailog;
    ArrayList<String> newsList = new ArrayList<>();
    ArrayList<String> newsUrlList = new ArrayList<>();
    ListView list;
    ArrayAdapter<String> arrayAdapter;
    String url = "https://content.guardianapis.com/search?&api-key=";
    String apiKey = "92512cff-e7af-45b3-b12c-1474da15a69e";
    String pageSize = "&page-size=20";
    String urlString = url + apiKey + pageSize;
    LoaderManager.LoaderCallbacks<String> loaderCallbacks = new LoaderManager.LoaderCallbacks<String>() {
        @Override
        public Loader<String> onCreateLoader(int id, Bundle args) {
            return new NewsLoader(getApplicationContext(), urlFinal);
        }

        @Override
        public void onLoadFinished(Loader<String> loader, String data) {
            progDailog.dismiss();
            arrayAdapter.clear();
            if (data != null) {
                try {
                    JSONObject reader = new JSONObject(data);
                    JSONObject newsArray = reader.getJSONObject("response");
                    int total = newsArray.getInt("total");
                    if (total == 0) {
                        list.setVisibility(View.INVISIBLE);
                        emptyView.setText(R.string.empty);
                    } else {
                        list.setVisibility(View.VISIBLE);
                        emptyView.setText("");
                        JSONArray newsResults = newsArray.getJSONArray("results");
                        for (int i = 0; i < 20; i++) {
                            JSONObject BookObject = newsResults.getJSONObject(i);
                            String news, section, url;

                            news = BookObject.getString("webTitle");
                            section = BookObject.getString("sectionName");
                            url = BookObject.getString("webUrl");

                            newsList.add(news + "\nSection: " + section);
                            newsUrlList.add(url);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                list.setAdapter(arrayAdapter);
            }
        }

        @Override
        public void onLoaderReset(Loader<String> loader) {
            arrayAdapter.clear();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        Button btn = (Button) findViewById(R.id.search_btn);
        emptyView = (TextView) findViewById(R.id.empty);

        if (savedInstanceState != null) {
            newsList = savedInstanceState.getStringArrayList("headerList");
            newsUrlList = savedInstanceState.getStringArrayList("urlList");
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = newsUrlList.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm =
                        (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    EditText query = (EditText) findViewById(R.id.query_et);
                    String inputQuery = query.getText().toString();
                    inputQuery = inputQuery.replace(" ", "+");
                    urlFinal = urlString + "&q=" + inputQuery;
//                    new ProcessJSON().execute(urlFinal);
                    getLoaderManager().initLoader(0, null, loaderCallbacks);
                    progDailog = new ProgressDialog(MainActivity.this);
                    progDailog.setMessage("Loading...");
                    progDailog.setIndeterminate(false);
                    progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progDailog.setCancelable(true);
                    progDailog.show();
                } else {
                    Toast.makeText(MainActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newsList);
        list.setAdapter(arrayAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("headerList", newsList);
        outState.putStringArrayList("urlList", newsUrlList);
        super.onSaveInstanceState(outState);
    }
}
