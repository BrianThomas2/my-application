package com.example.brayo.mealorderingmobileapplication.mFirebase;

import com.example.brayo.mealorderingmobileapplication.Mdata.FoodProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Brayo on 02/05/2018.
 */

public class FirebaseHelper {

    DatabaseReference databaseReference;
    Boolean saved = null;
    ArrayList<FoodProvider> foodProviders = new ArrayList<>();

    public FirebaseHelper(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public Boolean save(FoodProvider foodProvider)
    {
        if (foodProvider == null)
        {
            saved = false;
        }else
        {
            try {
                databaseReference.child("FoodProvider").push().setValue(foodProvider);
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }

    private void fetchData (DataSnapshot dataSnapshot)
    {
        foodProviders.clear();

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
        {
            FoodProvider foodProvider = dataSnapshot1.getValue(FoodProvider.class);
            foodProviders.add(foodProvider);
        }
    }

    public ArrayList<FoodProvider> retrive()
    {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return foodProviders;
    }
}
