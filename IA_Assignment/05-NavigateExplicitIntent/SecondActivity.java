package com.example.activitynavigator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button buttonGoToFirst; // Declare the button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout for this activity
        setContentView(R.layout.activity_second);

        // Initialize the button by finding its ID from the layout
        buttonGoToFirst = findViewById(R.id.buttonGoToFirst);

        // Set an OnClickListener for the button
        buttonGoToFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Explicit Intent to start MainActivity
                // This will take back to the first activity
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent); // Start the new activity
                // Optional: To close SecondActivity completely when going back
                // finish();
            }
        });
    }
}
