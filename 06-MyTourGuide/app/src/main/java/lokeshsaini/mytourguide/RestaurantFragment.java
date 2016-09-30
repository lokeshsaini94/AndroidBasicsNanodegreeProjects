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
public class RestaurantFragment extends Fragment {

    ListView listView;

    public RestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);

        listView = (ListView) rootView.findViewById(R.id.restaurant_list);

        final LocationDetails location_attractions_data[] = new LocationDetails[]
                {

                        new LocationDetails(getContext().getString(R.string.restaurant1Name),
                                getContext().getString(R.string.restaurant1Desc), R.drawable.michaeljackson),
                        new LocationDetails(getContext().getString(R.string.restaurant2Name),
                                getContext().getString(R.string.restaurant2Desc), R.drawable.android),
                        new LocationDetails(getContext().getString(R.string.restaurant3Name),
                                getContext().getString(R.string.restaurant3Desc), R.drawable.obama),
                        new LocationDetails(getContext().getString(R.string.restaurant4Name),
                                getContext().getString(R.string.restaurant4Desc), R.drawable.joker),
                        new LocationDetails(getContext().getString(R.string.restaurant5Name),
                                getContext().getString(R.string.restaurant5Desc), R.drawable.bean)
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
