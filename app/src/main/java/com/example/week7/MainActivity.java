package com.example.week7;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.week7.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EditText usernameEditText = findViewById(R.id.UsernameText);
        EditText passwordEditText = findViewById(R.id.passwordText);
        Button loginButton = findViewById(R.id.btnlogin);
        Button signupButon = findViewById(R.id.btnsignup);
        DB = new DatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = DB.fetchUser(
                        usernameEditText.getText().toString(),
                        passwordEditText.getText().toString()
                );

                if (result) {
                    Toast.makeText(MainActivity.this, "Successfully logged in!",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    Toast.makeText(MainActivity.this, "The user does not exist.",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        signupButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupintent= new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signupintent);
            }
        });
    }
}