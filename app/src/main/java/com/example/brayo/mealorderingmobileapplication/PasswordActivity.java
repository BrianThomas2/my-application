package com.example.brayo.mealorderingmobileapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {

    EditText editEmail;
    Button resetPassword;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        editEmail = (EditText)findViewById(R.id.editEmail);
        resetPassword = (Button) findViewById(R.id.resetPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = editEmail.getText().toString().trim();

                if (useremail.isEmpty()){
                    Toast.makeText(PasswordActivity.this, "Please enter your registered email", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(PasswordActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordActivity.this,LoginActivity.class));
                            }
                            else {
                                Toast.makeText(PasswordActivity.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
    }
}
