package lokeshsaini.myinventoryapp;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddItemActivity extends AppCompatActivity {
    public String price;
    public String countitem;
    public String name;
    public long nextID;
    DBHandler db = new DBHandler(this);
    Button addButton;
    Button imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        nextID = (db.rowCount() + 1);
        Intent intent = getIntent();
        String message = intent.getStringExtra("HEADER");
        setTitle(message);

        addButton = (Button) findViewById(R.id.submitAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameText = (EditText) findViewById(R.id.productName);
                EditText priceText = (EditText) findViewById(R.id.productPrice);
                EditText quantityText = (EditText) findViewById(R.id.productQuantity);
                ImageView imageView = (ImageView) findViewById(R.id.image);
                name = nameText.getText().toString();
                price = (priceText.getText().toString());
                countitem = (quantityText.getText().toString());

                if (nameText.getText().toString().length() == 0) {
                    nameText.setError("Invalid Name");
                } else if (priceText.getText().toString().length() == 0 || Integer.parseInt(priceText.getText().toString()) <= 0) {
                    priceText.setError("Invalid Price");
                } else if (quantityText.getText().toString().length() == 0 || Integer.parseInt(quantityText.getText().toString()) <= 0) {
                    quantityText.setError("Invalid Quantity");
                } else if (imageView.getDrawable() == null) {
                    Toast.makeText(getApplicationContext(), "Upload an image", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    db.addItem(new Product(name, Integer.parseInt(countitem), Double.parseDouble(price)));
                    Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        imageButton = (Button) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a Picture"), 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                ImageView imageView = (ImageView) findViewById(R.id.image);
                imageView.setImageBitmap(bitmap);
                String filename = Long.toString(nextID);
                saveImage(bitmap, filename);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to get image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveImage(Bitmap bitmap, String filename) {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File appDirectory = contextWrapper.getFilesDir();
        File currentPath = new File(appDirectory, filename);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(currentPath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
