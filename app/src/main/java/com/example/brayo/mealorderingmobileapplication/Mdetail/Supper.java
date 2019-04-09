package com.example.brayo.mealorderingmobileapplication.Mdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.brayo.mealorderingmobileapplication.Mbreakfast.Chap;
import com.example.brayo.mealorderingmobileapplication.Mlunch.Beans;
import com.example.brayo.mealorderingmobileapplication.Mlunch.Beef;
import com.example.brayo.mealorderingmobileapplication.Mlunch.Flour;
import com.example.brayo.mealorderingmobileapplication.Mlunch.Kab;
import com.example.brayo.mealorderingmobileapplication.Mlunch.Rice;
import com.example.brayo.mealorderingmobileapplication.R;

public class Supper extends AppCompatActivity {

    private TextView beefClicked,riceClicked,beansClicked,cabbageClicked,flourClicked,chapClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supper);

        beefClicked = (TextView)findViewById(R.id.beefClicked);
        beansClicked = (TextView)findViewById(R.id.beansClicked);
        riceClicked = (TextView)findViewById(R.id.riceClicked);
        chapClicked = (TextView)findViewById(R.id.chapClicked);
        cabbageClicked= (TextView)findViewById(R.id.cabbageClicked);
        flourClicked = (TextView)findViewById(R.id.flourClicked);

        beefClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breakfastIntent = new Intent(Supper.this,Beef.class);
                startActivity(breakfastIntent);
            }
        });
        beansClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breadIntent = new Intent(Supper.this,Beans.class);
                startActivity(breadIntent);
            }
        });
        riceClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Supper.this,Rice.class);
                startActivity(intent);
            }
        });
        cabbageClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Supper.this,Kab.class);
                startActivity(intent);
            }
        });
        chapClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Supper.this,Chap.class);
                startActivity(intent);
            }
        });
        flourClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Supper.this,Flour.class);
                startActivity(intent);
            }
        });

    }
}
