package com.example.measurementconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Import Toast for displaying messages

import java.text.DecimalFormat; // Import DecimalFormat for formatting output

public class MainActivity extends AppCompatActivity {

    // Declare UI elements
    private EditText cmEditText;
    private Button convertButton;
    private TextView inchesResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the layout for this activity

        // Initialize UI elements by finding their IDs from the layout
        cmEditText = findViewById(R.id.cmEditText);
        convertButton = findViewById(R.id.convertButton);
        inchesResultTextView = findViewById(R.id.inchesResultTextView);

        // Set an OnClickListener for the convert button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertCentimetersToInches(); // Call the conversion method
            }
        });
    }

    /**
     * This method handles the conversion from centimeters to inches.
     */
    private void convertCentimetersToInches() {
        // Get the input text from the EditText
        String cmString = cmEditText.getText().toString();

        // Check if the input string is empty
        if (cmString.isEmpty()) {
            // Show a Toast message if the input is empty
            Toast.makeText(this, "Please enter a value in Centimeters", Toast.LENGTH_SHORT).show();
            inchesResultTextView.setText("Result: "); // Clear previous result
            return; // Exit the method
        }

        try {
            // Parse the input string to a double
            double centimeters = Double.parseDouble(cmString);

            // Perform the conversion (1 inch = 2.54 cm)
            double inches = centimeters / 2.54;

            // Format the inches value to two decimal places
            // This makes the output cleaner and more readable
            DecimalFormat df = new DecimalFormat("#.##");
            String formattedInches = df.format(inches);

            // Display the result in the TextView
            inchesResultTextView.setText("Result: " + formattedInches + " inches");

        } catch (NumberFormatException e) {
            // Handle cases where the user inputs non-numeric data
            Toast.makeText(this, "Invalid input. Please enter a valid number.", Toast.LENGTH_LONG).show();
            inchesResultTextView.setText("Result: "); // Clear previous result
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
}
