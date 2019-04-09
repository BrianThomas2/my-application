package com.example.brayo.mealorderingmobileapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


import com.example.brayo.mealorderingmobileapplication.Fragments.Bills;
import com.example.brayo.mealorderingmobileapplication.Fragments.Call;
import com.example.brayo.mealorderingmobileapplication.Fragments.Meal;
import com.example.brayo.mealorderingmobileapplication.Fragments.SignOut;
import com.example.brayo.mealorderingmobileapplication.Mdetail.Breakfast;
import com.example.brayo.mealorderingmobileapplication.Mdetail.Lunch;
import com.example.brayo.mealorderingmobileapplication.Mdetail.Supper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference category;

    public Button buttonBreakfast;
    public Button buttonLunch;
    public Button buttonSupper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        buttonBreakfast = findViewById(R.id.buttonBreakfast);
        buttonLunch = findViewById(R.id.buttonLunch);
        buttonSupper = findViewById(R.id.buttonSupper);

            buttonBreakfast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent buttonBreakfast = new Intent(NavDrawer.this,Breakfast.class);
                    startActivity(buttonBreakfast);
                }
            });
            buttonLunch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent buttonLunch = new Intent(NavDrawer.this,Lunch.class);
                    startActivity(buttonLunch);
                }
            });
            buttonSupper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent buttonSupper = new Intent(NavDrawer.this,Supper.class);
                    startActivity(buttonSupper);
                }
            });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavDrawer.this,Cart.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(NavDrawer.this, Cart.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void displaySelectedScreen(int id){
        Fragment fragment = null;

        switch (id){
            case R.id.nav_menu:
                fragment = new com.example.brayo.mealorderingmobileapplication.Fragments.Menu();
                break;
            case R.id.nav_call:
                fragment = new Call();
                break;
            case R.id.nav_pay:
                fragment = new Bills();
                break;
            case R.id.nav_orders:
                fragment = new Meal();
                break;
            case R.id.nav_logout:
                fragment = new SignOut();
                break;

        }

        if(fragment != null){
            android.support.v4.app.FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.Screen_area, fragment);
            ft.commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        displaySelectedScreen(id);

        return true;
    }
}
