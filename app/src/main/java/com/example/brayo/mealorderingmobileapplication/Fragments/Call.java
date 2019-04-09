package com.example.brayo.mealorderingmobileapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brayo.mealorderingmobileapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Brayo on 22/02/2018.
 */

public class Call extends Fragment {

     EditText name,email,phone,message;
     Button button;

     FirebaseDatabase database;
     DatabaseReference myRef;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Call");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.call, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        message = view.findViewById(R.id.message);
        button = view.findViewById(R.id.buttonSubmit);

        database = FirebaseDatabase.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String child = name.getText().toString();
                myRef = database.getReference("Messages").child(child);

                myRef.child("Name").setValue(name.getText().toString());
                myRef.child("Email").setValue(email.getText().toString());
                myRef.child("Phone").setValue(phone.getText().toString());
                myRef.child("Message").setValue(message.getText().toString());



            }
        });




        return view;

    }


}
