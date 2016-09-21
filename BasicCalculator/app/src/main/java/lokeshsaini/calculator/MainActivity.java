package lokeshsaini.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    public String str ="";
    public String displayString = "";
    Character op = 'q';
    long num, numtemp=0, numtemp2=0;
    TextView showResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showResult = (TextView)findViewById(R.id.result_id);
    }

    public void btn1Clicked(View v){
        insert(1);
        displayString = displayString + "1";
        displayEq();
    }

    public void btn2Clicked(View v){
        insert(2);
        displayString = displayString + "2";
        displayEq();
    }

    public void btn3Clicked(View v){
        insert(3);
        displayString = displayString + "3";
        displayEq();
    }

    public void btn4Clicked(View v){
        insert(4);
        displayString = displayString + "4";
        displayEq();
    }

    public void btn5Clicked(View v){
        insert(5);
        displayString = displayString + "5";
        displayEq();
    }

    public void btn6Clicked(View v){
        insert(6);
        displayString = displayString + "6";
        displayEq();
    }

    public void btn7Clicked(View v){
        insert(7);
        displayString = displayString + "7";
        displayEq();
    }

    public void btn8Clicked(View v){
        insert(8);
        displayString = displayString + "8";
        displayEq();
    }

    public void btn9Clicked(View v){
        insert(9);
        displayString = displayString + "9";
        displayEq();
    }

    public void btn0Clicked(View v){
        insert(0);
        displayString = displayString + "0";
        displayEq();
    }

    public void btnplusClicked(View v){
        calculate();
        perform();
        op = '+';
        displayString = displayString + " + ";
        displayEq();
    }

    public void btnminusClicked(View v){
        calculate();
        perform();
        op = '-';
        displayString = displayString + " - ";
        displayEq();
    }

    public void btndivideClicked(View v){
        calculate();
        perform();
        op = '/';
        displayString = displayString + " / ";
        displayEq();
    }

    public void btnmultiClicked(View v){
        calculate();
        perform();
        op = '*';
        displayString = displayString + " * ";
        displayEq();
    }

    public void btnequalClicked(View v){
        calculate();
        displayResult();
        str ="";
        op ='q';
        num = 0;
        numtemp = 0;
        displayString = "";
    }

    public void btnclearClicked(View v){
        reset();
    }

    private void reset() {
        str ="";
        op ='q';
        num = 0;
        numtemp = 0;
        showResult.setText("");
        displayString = "";
        displayEq();
    }

    private void insert(long j) {
        if (j>=0 && j < Long.MAX_VALUE - 1) {
            str = str + Long.toString(j);
            num = Long.valueOf(str).longValue();
            showResult.setText(str);
        }
    }

    private void perform() {
        str = "";
        numtemp = num;
    }

    private void calculate() {
        if(op == '+')
            num = numtemp+num;
        else if(op == '-')
            num = numtemp-num;
        else if(op == '/')
            num = numtemp/num;
        else if(op == '*')
            num = numtemp*num;
        numtemp2 = num;
    }

    // Displays the equation
    private void displayResult() {
        showResult.setText(""+numtemp2);
    }

    // Displays the equation
    private void displayEq() {
        TextView aTextView = (TextView) findViewById(R.id.display);
        aTextView.setText(displayString);
    }

}