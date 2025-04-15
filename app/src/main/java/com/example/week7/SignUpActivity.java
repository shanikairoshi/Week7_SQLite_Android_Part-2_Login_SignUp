package com.example.week7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.week7.data.DatabaseHelper;
import com.example.week7.model.User;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        EditText usernameEditText = findViewById(R.id.SUsernameeditText);
        EditText passwordEditText = findViewById(R.id.sPasswordeditText);
        EditText confirmPasswordEditText = findViewById(R.id.sConfirmPasswordeditText);

        Button saveButton = findViewById(R.id.savebtn);
        db= new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (password.equals(confirmPassword)) {
                    long result = db.insertUser(new User(username, password));

                    if (result > 0) {
                        Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "Two passwords do not match", Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}