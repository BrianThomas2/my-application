package com.example.brayo.mealorderingmobileapplication.Mdetail;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.brayo.mealorderingmobileapplication.Mbreakfast.Bread;
import com.example.brayo.mealorderingmobileapplication.Mbreakfast.Chap;
import com.example.brayo.mealorderingmobileapplication.Mbreakfast.Mandazi;
import com.example.brayo.mealorderingmobileapplication.Mbreakfast.Milk;
import com.example.brayo.mealorderingmobileapplication.Mbreakfast.Sausage;
import com.example.brayo.mealorderingmobileapplication.Mbreakfast.Tea;
import com.example.brayo.mealorderingmobileapplication.R;


public class Breakfast extends AppCompatActivity {

     private TextView sausageClicked,breadClicked,mandaziClicked,chapClicked,milkClicked,teaClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        sausageClicked = (TextView)findViewById(R.id.sausageClicked);
        mandaziClicked = (TextView)findViewById(R.id.mandaziClicked);
        chapClicked = (TextView)findViewById(R.id.chapClicked);
        milkClicked = (TextView)findViewById(R.id.milkClicked);
        teaClicked = (TextView)findViewById(R.id.teaClicked);





        sausageClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breakfastIntent = new Intent(Breakfast.this,Sausage.class);
                startActivity(breakfastIntent);
            }
        });

        breadClicked = (TextView)findViewById(R.id.breadClicked);

        breadClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breadIntent = new Intent(Breakfast.this,Bread.class);
                startActivity(breadIntent);
            }
        });
        mandaziClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Breakfast.this,Mandazi.class);
                startActivity(intent);
            }
        });
        teaClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Breakfast.this,Tea.class);
                startActivity(intent);
            }
        });
        chapClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Breakfast.this,Chap.class);
                startActivity(intent);
            }
        });
        milkClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Breakfast.this,Milk.class);
                startActivity(intent);
            }
        });



    }

}
