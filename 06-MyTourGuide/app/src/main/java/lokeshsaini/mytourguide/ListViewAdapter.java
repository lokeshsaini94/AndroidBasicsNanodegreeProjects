package lokeshsaini.mytourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private final LocationDetails [] locationDetails;


    public ListViewAdapter(Context c, LocationDetails[] locationDetails) {
        mContext = c;
        this.locationDetails = locationDetails;

    }
    public int getCount() { // TODO Auto-generated method stub

        return locationDetails.length;
    }
    @Override
    public Object getItem(int position) { // TODO Auto-generated method stub

        return null;
    }
    @Override
    public long getItemId(int position) { // TODO Auto-generated method stub

        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // TODO Auto-generated method stub

        View list;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            list = new View(mContext);
            list = inflater.inflate(R.layout.listview_layout, null);
            TextView name = (TextView) list.findViewById(R.id.location_name);
            TextView desc = (TextView) list.findViewById(R.id.location_desc);
            ImageView img = (ImageView) list.findViewById(R.id.location_image);

            name.setText(locationDetails[position].getLocationName());
            desc.setText(locationDetails[position].getLocationDesc());
            img.setImageResource(locationDetails[position].getLocationIcon());


        } else {
            list = convertView;
        }
        return list;
    }
}