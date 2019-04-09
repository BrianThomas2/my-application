package com.example.brayo.mealorderingmobileapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.brayo.mealorderingmobileapplication.LoginActivity;
import com.example.brayo.mealorderingmobileapplication.R;
import com.google.firebase.auth.FirebaseAuth;


public class SignOut extends Fragment implements View.OnClickListener {

    private Button button;
    private FirebaseAuth firebaseAuth;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_logout, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Bills");

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity

            getActivity().finish();
            //starting login activity
            startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
        }
        button = (Button) getActivity().findViewById(R.id.buttonLogOut);

        button.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        if (v == button)
        {
            //logout user
            firebaseAuth.signOut();
            //close Activity
            getActivity().finish();
            //start login activity
            startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
        }

    }
}
