package com.example.bmicalculator; // Ensure this matches your package name

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Import Toast for displaying messages

import java.text.DecimalFormat; // Import DecimalFormat for precise formatting if needed, though String.format is often sufficient

public class MainActivity extends AppCompatActivity {
    // Declare UI elements
    EditText etWeight, etHeight;
    Button btnCalculateBMI;
    TextView tvBMIResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout defined in activity_main.xml
        setContentView(R.layout.activity_main);

        // Initialize UI elements by finding their respective IDs from the layout
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        btnCalculateBMI = findViewById(R.id.btnCalculateBMI);
        tvBMIResult = findViewById(R.id.tvBMIResult);

        // Set an OnClickListener for the Calculate BMI button
        btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi(); // Call the method to handle BMI calculation
            }
        });
    }

    /**
     * This method handles the BMI calculation logic, including input validation
     * and displaying the result.
     */
    private void calculateBmi() {
        // Get the input strings from the EditText fields
        String weightStr = etWeight.getText().toString();
        String heightStr = etHeight.getText().toString();

        // Check if either input field is empty
        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both weight and height.", Toast.LENGTH_SHORT).show();
            tvBMIResult.setText("Result: "); // Clear previous result
            return; // Exit the method if input is incomplete
        }

        try {
            // Parse the input strings to float values
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);

            // Check if height is zero to prevent division by zero error
            if (height == 0) {
                Toast.makeText(this, "Height cannot be zero.", Toast.LENGTH_SHORT).show();
                tvBMIResult.setText("Result: "); // Clear previous result
                return; // Exit the method
            }

            // Calculate BMI: weight (kg) / (height (m) * height (m))
            float bmi = weight / (height * height);

            // Get the health assessment based on BMI
            String assessment = getHealthAssessment(bmi);

            // Format and display the BMI result and assessment
            // Corrected String.format pattern: "%.2f" for float, "%s" for string
            tvBMIResult.setText(String.format("BMI: %.2f (%s)", bmi, assessment));

        } catch (NumberFormatException e) {
            // Catch NumberFormatException if the input is not a valid number
            Toast.makeText(this, "Invalid input. Please enter valid numbers for weight and height.", Toast.LENGTH_LONG).show();
            tvBMIResult.setText("Result: "); // Clear previous result
            e.printStackTrace(); // Print stack trace for debugging purposes
        }
    }

    /**
     * Determines the health assessment based on the calculated BMI.
     * @param bmi The calculated Body Mass Index.
     * @return A string indicating the health category (e.g., "Underweight", "Normal").
     */
    private String getHealthAssessment(float bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
