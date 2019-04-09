package com.example.brayo.mealorderingmobileapplication.Mbreakfast;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brayo.mealorderingmobileapplication.Cart;
import com.example.brayo.mealorderingmobileapplication.Mdata.FoodDbHelper;
import com.example.brayo.mealorderingmobileapplication.Mdata.FoodProvider;
import com.example.brayo.mealorderingmobileapplication.Model.Request;
import com.example.brayo.mealorderingmobileapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;

/**
 * Created by Brayo on 21/04/2018.
 */

public class Chap extends AppCompatActivity {

    TextView description;
    ImageView foodImage;
    Context context = this;
    FoodDbHelper foodDbHelper;
    SQLiteDatabase sqLiteDatabase;
    private int mQuantity = 1;
    private double mTotalPrice = 20;
    TextView costTextView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    Button addToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);

        foodImage = (ImageView) findViewById(R.id.foodImage);
        description = (TextView)findViewById(R.id.description);
        addToCartButton = (Button) findViewById(R.id.cart_button);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Requests");

        costTextView = (TextView) findViewById(
                R.id.cost_text_view);


        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        if (mQuantity == 1){

            mTotalPrice = 20;
            displayCost(mTotalPrice);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_drawer, menu);

        // Get the notifications MenuItem and
        // its LayerDrawable (layer-list)
        MenuItem item = menu.findItem(R.id.action_settings);
        LayerDrawable icon = (LayerDrawable) item.getIcon();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent intent = new Intent(this, Cart.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void increment(View view){

        mQuantity = mQuantity + 1;
        displayQuantity(mQuantity);
        mTotalPrice = mQuantity * 20;
        displayCost(mTotalPrice);
    }

    public void decrement(View view){
        if (mQuantity > 1){

            mQuantity = mQuantity - 1;
            displayQuantity(mQuantity);
            mTotalPrice = mQuantity * 20;
            displayCost(mTotalPrice);

        }
    }

    private void displayQuantity(int numberOfItems) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfItems));
    }

    private void displayCost(double totalPrice) {

        String mTotalPrice = String.valueOf(totalPrice);
        costTextView.setText(mTotalPrice);
    }

    private void showAlertDialog() {

        String desc = description.getText().toString();
        String quantity = String.valueOf(mQuantity);
        String totalprice = String.valueOf(mTotalPrice);
        foodDbHelper = new FoodDbHelper(context);
        sqLiteDatabase = foodDbHelper.getWritableDatabase();
        foodDbHelper.addFood(desc,quantity,totalprice,sqLiteDatabase);

        foodDbHelper.close();
        String id = databaseReference.push().getKey();

        FoodProvider foodProvider = new FoodProvider(desc,quantity,totalprice);
        databaseReference.child(id).setValue(foodProvider);

        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(Chap.this);
        alertDialog.setTitle("One more step");
        alertDialog.setMessage("Enter the Table number");

        final EditText editTable = new EditText(Chap.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        editTable.setLayoutParams(lp);
        alertDialog.setView(editTable);
        alertDialog.setIcon(R.drawable.ic_shopping_cart);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Request request = new Request(
                        editTable.getText().toString()
                );

                databaseReference.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);

                Toast.makeText(Chap.this, "Thank you,Order Placed", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
