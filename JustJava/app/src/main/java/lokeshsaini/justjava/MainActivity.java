package lokeshsaini.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Declaring global variables.
     */
    int quantity = 1;
    int originalPricePerCoffee = 50;
    int whippedCreamBoolean = 0;
    int chocolateBoolean = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display();
        displaySummary();
    }

    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity > 10) {
            Toast.makeText(getApplicationContext(), getString(R.string.max_coffee), Toast.LENGTH_SHORT).show();
            quantity = 10;
        }
        display();
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        if (quantity < 1) {
            Toast.makeText(getApplicationContext(), getString(R.string.min_coffee), Toast.LENGTH_SHORT).show();
            quantity = 1;
        }
        display();
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void whippedCreamOnClick(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        if (whippedCreamCheckbox.isChecked())
            whippedCreamBoolean = 1;
        else
            whippedCreamBoolean = 0;
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void chocolateOnClick(View view) {
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (chocolateCheckbox.isChecked())
            chocolateBoolean = 1;
        else
            chocolateBoolean = 0;
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public int calculatePrice() {
        int pricePerCoffee = originalPricePerCoffee;
        if (whippedCreamBoolean == 1) {
            pricePerCoffee += 5;
        }
        if (chocolateBoolean == 1) {
            pricePerCoffee += 10;
        }
        if (whippedCreamBoolean == 0 && chocolateBoolean == 0) {
            pricePerCoffee = originalPricePerCoffee;
        }
        return pricePerCoffee * quantity;
    }

    /**
     * This method displays the quantity and final price
     */
    public void updateSummary(View view) {
        display();
        displaySummary();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displaySummary() {
        TextView summaryTextView = (TextView) findViewById(R.id.summary_text_view);
        summaryTextView.setText(createOrderSummary());
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private String createOrderSummary() {
        EditText nameFeild = (EditText) findViewById(R.id.name_feild);
        String name = nameFeild.getText().toString();

        String priceMessage = getString(R.string.order_summary_name) + name;
        priceMessage += getString(R.string.order_summary_quantity) + quantity;
        priceMessage += getString(R.string.order_summary_per_coffee) + NumberFormat.getCurrencyInstance().format(originalPricePerCoffee);
        if (whippedCreamBoolean == 1) {
            priceMessage += getString(R.string.order_summary_whipped_cream);
        }
        if (chocolateBoolean == 1) {
            priceMessage += getString(R.string.order_summary_chocolate);
        }
        priceMessage += getString(R.string.order_summary_total_bill) + NumberFormat.getCurrencyInstance().format(calculatePrice());
        priceMessage += getString(R.string.order_summary_thank_you);
        return priceMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        display();
        displaySummary();
        Toast.makeText(getApplicationContext(), getString(R.string.email_summary_that_will_be) + calculatePrice(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_summary_app_credits));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}