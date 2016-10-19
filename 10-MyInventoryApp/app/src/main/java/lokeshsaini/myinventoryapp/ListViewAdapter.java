package lokeshsaini.myinventoryapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListViewAdapter extends BaseAdapter {

    private static final String TAG = ListViewAdapter.class.getSimpleName();
    ArrayList<Product> listArray;

    public ListViewAdapter(ArrayList<Product> listArray) {
        this.listArray = new ArrayList<Product>(listArray);
    }

    @Override
    public int getCount() {
        return listArray.size();
    }

    @Override
    public Object getItem(int i) {
        return listArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.item_list_view, parent, false);
        }

        final Product dataModel = listArray.get(index);

        final TextView productName = (TextView) view.findViewById(R.id.productName);
        productName.setText(dataModel.getProductName());

        final TextView productAvailable = (TextView) view.findViewById(R.id.productAvailable);
        productAvailable.setText("" + dataModel.getQuantity());

        final TextView productPrice = (TextView) view.findViewById(R.id.productPrice);
        productPrice.setText("$" + dataModel.getPrice());

        this.notifyDataSetChanged();

        Button saleButton = (Button) view.findViewById(R.id.saleButton);

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler db = new DBHandler(v.getContext());
                if (dataModel.getQuantity() != 0) {
                    dataModel.itemSale();
                    Toast.makeText(parent.getContext(), "One item of " + dataModel.getProductName() + " sold.\nAvailable Quantity: " + dataModel.getQuantity(), Toast.LENGTH_SHORT).show();
                } else if (dataModel.getQuantity() == 0) {
                    Toast.makeText(parent.getContext(), "No more item(s) of " + dataModel.getProductName() + " left out.\n Order Now !!! ", Toast.LENGTH_SHORT).show();
                }
                db.updateHabitRow(dataModel.getProductId(), dataModel);
                productAvailable.setText("" + dataModel.getQuantity());
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent details = new Intent(parent.getContext(), EditItemActivity.class);
                details.putExtra("productName", dataModel.getProductName());
                details.putExtra("productPrice", dataModel.getPrice());
                details.putExtra("productQuantity", dataModel.getQuantity());
                details.putExtra("id", dataModel.getProductId());
                parent.getContext().startActivity(details);
            }
        });

        return view;
    }
}
