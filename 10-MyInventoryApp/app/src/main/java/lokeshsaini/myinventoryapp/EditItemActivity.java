package lokeshsaini.myinventoryapp;

import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class EditItemActivity extends AppCompatActivity {
    public int rowID;
    String name;
    TextView price;
    TextView quantity;
    Button save;
    Button delete;
    int quantityInt;
    int quantityOld;
    Button add;
    Button less;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        price = (TextView) findViewById(R.id.productPrice);
        quantity = (TextView) findViewById(R.id.productQuantity);
        save = (Button) findViewById(R.id.saveButton);
        delete = (Button) findViewById(R.id.deleteButton);
        less = (Button) findViewById(R.id.less);
        add = (Button) findViewById(R.id.add);

        // Get data passed in from Fragment
        Intent details = getIntent();
        setTitle(details.getStringExtra("productName"));
        rowID = details.getIntExtra("id", 0);
        name = details.getStringExtra("productName");
        price.setText("" + details.getDoubleExtra("productPrice", 0.0));
        quantityInt = quantityOld = details.getIntExtra("productQuantity", 0);

        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File appDirectory = contextWrapper.getFilesDir();
        String imageLocationDir = appDirectory.toString();
        rowID = details.getIntExtra("id", 0);
        String imagePath = imageLocationDir + "/" + rowID;
        ImageView imageView = (ImageView) findViewById(R.id.productPicture);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        imageView.setImageBitmap(bitmap);

        quantity.setText("" + quantityInt);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityInt == 0) {
                    Toast.makeText(EditItemActivity.this, "No negative values", Toast.LENGTH_SHORT).show();
                    quantityInt = 0;
                    return;
                }
                quantityInt = quantityInt - 1;
                quantity.setText("" + quantityInt);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityInt == 100) {
                    Toast.makeText(EditItemActivity.this, "Maximum of 100 items", Toast.LENGTH_SHORT).show();
                    quantityInt = 100;
                    return;
                }
                quantityInt = quantityInt + 1;
                quantity.setText("" + quantityInt);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity.getText().toString().length() == 0 || Integer.parseInt(quantity.getText().toString()) <= 0) {
                    quantity.setError("Invalid Quantity");
                } else {
                    saveItem(rowID);
                    if ((quantityInt - quantityOld) < 1) {
                        finish();
                    } else {
                        String subject = "Order for " + (quantityInt - quantityOld) + " more of " + name;
                        String message = "Name: " + name +
                                "\nPrice: " + price.getText().toString() +
                                "\nQuantity: " + (quantityInt - quantityOld);
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
                        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                        intent.putExtra(Intent.EXTRA_TEXT, message);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                        finish();
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(EditItemActivity.this)
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to delete this record permanently?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteItem(rowID);
                                Toast.makeText(v.getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(v.getContext(), MainActivity.class);
                                v.getContext().startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    public void saveItem(int rowID) {
        DBHandler db = new DBHandler(this);
        String mName = name;
        double mPrice = Double.parseDouble(price.getText().toString());
        Product product = new Product(mName, quantityInt, mPrice);
        db.updateHabitRow(rowID, product);
    }

    public void deleteItem(int rowID) {
        DBHandler db = new DBHandler(this);
        db.deleteHabitRow(rowID);

        Intent details = getIntent();
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File appDirectory = contextWrapper.getFilesDir();
        String imageLocationDir = appDirectory.toString();
        rowID = details.getIntExtra("id", 0);
        String imagePath = imageLocationDir + "/" + rowID;
        File file = new File(imagePath);
        boolean deleted = file.delete();
    }
}
