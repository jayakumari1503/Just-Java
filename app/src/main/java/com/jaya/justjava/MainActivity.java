package com.jaya.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Variables
    EditText name;
    CheckBox checkBoxForWhippedCream, checkBoxForChocolate;
    Button decBtn, incBtn, orderBtn;
    int qty = 0;
    TextView qtyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization variables
        name = (EditText) findViewById(R.id.editName);
        checkBoxForWhippedCream = (CheckBox) findViewById(R.id.checkbox1);
        checkBoxForChocolate = (CheckBox) findViewById(R.id.checkbox2);
        incBtn = (Button) findViewById(R.id.incbtn);
        decBtn = (Button) findViewById(R.id.decbtn);
        qtyTv = (TextView) findViewById(R.id.textView2);
        orderBtn = (Button) findViewById(R.id.orderbtn);


        //for quantity increment when incBtn clicked
        incBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty++;
                qtyTv.setText(" " + qty);
            }
        });

        //for quantity decrement when decBtn clicked
        decBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty > 0) {
                    qty--;
                    qtyTv.setText(" " + qty);
                }
            }
        });

        //display order summary when order button is clicked.
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendTextToEmail();
            }
        });
    }

    private void sendTextToEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "For order coffee");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message());
        emailIntent.setPackage("com.google.android.gm");
        startActivity(emailIntent);
        finish();
        Log.i("Finished sending email", "");
    }

    private String message() {

        //display the text that you entered in edit text
        String resultName = name.getText().toString();


        boolean hasWhippedCream = checkBoxForWhippedCream.isChecked();
        boolean hasChocolate = checkBoxForChocolate.isChecked();

        //display total price
        int price = qty * 5;

        //order message which displays on email
        String orderMessage = "Name:" + resultName;
        orderMessage += "\nAdd whipped cream?" + hasWhippedCream;
        orderMessage += "\nAdd chocolate?" + hasChocolate;
        orderMessage += "\nQuantity:" + qty;
        orderMessage += "\nTotal: $" + price;
        orderMessage += "\nThank you!";

        return orderMessage;
    }
}