package com.example.brayo.mealorderingmobileapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brayo.mealorderingmobileapplication.Common.Common;
import com.example.brayo.mealorderingmobileapplication.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView textViewPass;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        // ...
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            //close the activity
            finish();
            //start nav drawer here
            startActivity(new Intent(getApplicationContext(), NavDrawer.class));

        }

        buttonLogIn = (Button) findViewById(R.id.buttonLogIn);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
        textViewPass = (TextView) findViewById(R.id.textViewPass);

        progressDialog = new ProgressDialog(this);

        buttonLogIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);


        textViewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,PasswordActivity.class));
            }
        });

    }

    //method for student login
    private void StudentLogIn(){
        final String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Student login...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successful
                        if(task.isSuccessful()){
                            //start the NavDrawer activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), NavDrawer.class));


                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogIn){
            StudentLogIn();

        }

        if(view == textViewSignUp){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
