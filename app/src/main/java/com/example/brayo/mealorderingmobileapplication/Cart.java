package com.example.brayo.mealorderingmobileapplication;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.brayo.mealorderingmobileapplication.Mdata.FoodDbHelper;
import com.example.brayo.mealorderingmobileapplication.Mdata.FoodProvider;
import com.example.brayo.mealorderingmobileapplication.mFirebase.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class Cart extends AppCompatActivity {


    /**
     * Adapter for the ListView
     */
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    FoodDbHelper foodDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    FirebaseHelper helper;


    TextView foodDescription, quantity, price, txtTotalPrice;

    FirebaseDatabase database;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Requests");
        helper = new FirebaseHelper(databaseReference);

        txtTotalPrice = findViewById(R.id.total);
        foodDescription = findViewById(R.id.foodDescription);
        quantity = findViewById(R.id.quantity);
        price = findViewById(R.id.price);

        listView = findViewById(R.id.listView);
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.cart_layout);
        listView.setAdapter(listDataAdapter);
        foodDbHelper = new FoodDbHelper(getApplicationContext());
        sqLiteDatabase = foodDbHelper.getReadableDatabase();
        cursor = foodDbHelper.getFood(sqLiteDatabase);

        if (cursor.moveToFirst()) {
            do {
                String desc, quantity, totalprice;
                desc = cursor.getString(0);
                quantity = cursor.getString(1);
                totalprice = cursor.getString(2);
                FoodProvider foodProvider = new FoodProvider(desc, quantity, totalprice);
                listDataAdapter.add(foodProvider);

            } while (cursor.moveToNext());
        }


    }


}
