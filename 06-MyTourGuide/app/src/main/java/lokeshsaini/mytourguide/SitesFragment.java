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
public class SitesFragment extends Fragment {

    ListView listView;

    public SitesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sites, container, false);

        listView = (ListView) rootView.findViewById(R.id.sites_list);

        final LocationDetails location_attractions_data[] = new LocationDetails[]
                {

                        new LocationDetails(getContext().getString(R.string.sites1Name),
                                getContext().getString(R.string.sites1Desc), R.drawable.sites1),
                        new LocationDetails(getContext().getString(R.string.sites2Name),
                                getContext().getString(R.string.sites2Desc), R.drawable.sites2),
                        new LocationDetails(getContext().getString(R.string.sites3Name),
                                getContext().getString(R.string.sites3Desc), R.drawable.joker),
                        new LocationDetails(getContext().getString(R.string.sites4Name),
                                getContext().getString(R.string.sites4Desc), R.drawable.bean),
                        new LocationDetails(getContext().getString(R.string.sites5Name),
                                getContext().getString(R.string.sites5Desc), R.drawable.michaeljackson)
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
