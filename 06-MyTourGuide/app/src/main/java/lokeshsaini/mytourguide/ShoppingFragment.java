package lokeshsaini.mytourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment {

    ListView listView;

    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_shopping, container, false);

        listView = (ListView) rootView.findViewById(R.id.shopping_list);

        final LocationDetails location_attractions_data[] = new LocationDetails[]
                {

                        new LocationDetails(getContext().getString(R.string.shopping1Name),
                                getContext().getString(R.string.shopping1Desc), R.drawable.shopping1),
                        new LocationDetails(getContext().getString(R.string.shopping2Name),
                                getContext().getString(R.string.shopping2Desc), R.drawable.shopping2),
                        new LocationDetails(getContext().getString(R.string.shopping3Name),
                                getContext().getString(R.string.shopping3Desc), R.drawable.shopping3),
                        new LocationDetails(getContext().getString(R.string.shopping4Name),
                                getContext().getString(R.string.shopping4Desc), R.drawable.shopping4),
                        new LocationDetails(getContext().getString(R.string.shopping5Name),
                                getContext().getString(R.string.shopping5Desc), R.drawable.shopping5)
                };

        listView.setAdapter(new ListViewAdapter(rootView.getContext(), location_attractions_data));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                LocationDetails current = location_attractions_data[position];
                Toast.makeText(view.getContext(), current.getLocationName(), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

}
