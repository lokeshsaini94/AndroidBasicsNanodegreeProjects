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
public class EventFragment extends Fragment {

    ListView listView;

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        listView = (ListView) rootView.findViewById(R.id.event_list);

        final LocationDetails location_attractions_data[] = new LocationDetails[]
                {

                        new LocationDetails(getContext().getString(R.string.event1Name),
                                getContext().getString(R.string.event1Desc), R.drawable.bean),
                        new LocationDetails(getContext().getString(R.string.event2Name),
                                getContext().getString(R.string.event2Desc), R.drawable.joker),
                        new LocationDetails(getContext().getString(R.string.event3Name),
                                getContext().getString(R.string.event3Desc), R.drawable.michaeljackson),
                        new LocationDetails(getContext().getString(R.string.event4Name),
                                getContext().getString(R.string.event4Desc), R.drawable.obama),
                        new LocationDetails(getContext().getString(R.string.event5Name),
                                getContext().getString(R.string.event5Desc), R.mipmap.ic_launcher)
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
