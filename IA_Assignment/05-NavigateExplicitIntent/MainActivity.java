package com.example.activitynavigator;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonGoToSecond; // Declare the button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout for this activity
        setContentView(R.layout.activity_main);

        // Initialize the button by finding its ID from the layout
        buttonGoToSecond = findViewById(R.id.buttonGoToSecond);

        // Set an OnClickListener for the button
        buttonGoToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating an Explicit Intent to start SecondActivity
                // The constructor takes two arguments:
                // 1. Context (usually 'this' for the current Activity)
                // 2. The Class of the Activity to start (SecondActivity.class)
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent); // Start the new activity
            }
        });
    }
}
