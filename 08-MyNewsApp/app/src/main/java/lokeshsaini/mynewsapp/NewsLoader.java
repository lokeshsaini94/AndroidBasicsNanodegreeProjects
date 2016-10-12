package lokeshsaini.mynewsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;


public class NewsLoader extends AsyncTaskLoader<String> {

    String s;

    public NewsLoader(Context context, String s) {
        super(context);
        this.s = s;
    }

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        String stream = null;
        String urlString = s;

        HTTPDataHandler hh = new HTTPDataHandler();
        stream = hh.GetHTTPData(urlString);

        return stream;
    }

    @Override
    public void deliverResult(String data) {
        super.deliverResult(data);
    }
}