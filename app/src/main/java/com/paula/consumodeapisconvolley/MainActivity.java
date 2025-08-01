package com.paula.consumodeapisconvolley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonGet;
    private Button buttonPost;
    private Button buttonPut;
    private Button buttonDelete; // Button for the DELETE operation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGet = findViewById(R.id.buttonGet);
        buttonPost = findViewById(R.id.buttonPost);
        buttonPut = findViewById(R.id.buttonPut);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetActivity.class);
                startActivity(intent); // Launch GetActivity
            }
        });

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent); // Launch PostActivity
            }
        });

        // Set up the click listener for the PUT button
        buttonPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start PutActivity
                Intent intent = new Intent(MainActivity.this, PutActivity.class);
                startActivity(intent); // Launch PutActivity
            }
        });

        // Set up the click listener for the DELETE button
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start DeleteActivity
                // IMPORTANT: Changed 'Delete.class' to 'DeleteActivity.class' for consistency
                Intent intent = new Intent(MainActivity.this, Delete.class);
                startActivity(intent); // Launch DeleteActivity
            }
        });
    }
}