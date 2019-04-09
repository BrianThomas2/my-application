package com.example.brayo.mealorderingmobileapplication;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // ...
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null) {
            //start navDrawer here
            finish();
            //opening navDrawer activity
            startActivity(new Intent(getApplicationContext(), NavDrawer.class));
        }



        progressDialog = new ProgressDialog(this);


        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);

        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

    private  void  registerStudent(){
        String email=editTextEmail.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){

            Toast.makeText(this, "enter your email", Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "enter your password", Toast.LENGTH_SHORT).show();
            return;


        }
        progressDialog.setMessage("registering user....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            finish();
                            //opening Logout
                            // activity
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                        else
                            Toast.makeText(MainActivity.this, "could not register..", Toast.LENGTH_SHORT).show();

                    }
                });

    }


    @Override
    public void onClick(View v) {

        if (v == buttonRegister){
            registerStudent();
        }
        if (v == textViewSignIn){
            //will open login activity
            startActivity(new Intent(this,LoginActivity.class));
        }

    }
}
