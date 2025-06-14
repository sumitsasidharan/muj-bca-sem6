package com.example.todolistapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast; // Import Toast for displaying messages
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declare UI elements
    private EditText editTextTask;
    private Button buttonAdd;
    private ListView listViewTasks;
    // ArrayList to hold the tasks
    private ArrayList<String> tasks;
    // Custom adapter for the ListView
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the main layout for this activity
        setContentView(R.layout.activity_main);

        // Initialize UI elements by finding their IDs from the layout
        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewTasks = findViewById(R.id.listViewTasks);

        // Initialize the ArrayList for tasks
        tasks = new ArrayList<>();

        // Initialize the custom TaskAdapter, linking it to the list_item layout
        // R.layout.list_item refers to the layout defined in list_item.xml for each row
        adapter = new TaskAdapter(this, R.layout.list_item, tasks);

        // Set the adapter to the ListView
        listViewTasks.setAdapter(adapter);

        // Set an OnClickListener for the Add button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask(); // Call the method to add a new task
            }
        });
    }

    /**
     * Adds a new task to the list if the input is not empty.
     */
    private void addTask() {
        // Get the text from the EditText field
        String task = editTextTask.getText().toString().trim(); // Use trim() to remove leading/trailing spaces

        // Check if the task input is not empty
        if (!task.isEmpty()) {
            tasks.add(task); // Add the new task to the ArrayList
            adapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed to refresh the ListView
            editTextTask.setText(""); // Clear the EditText field after adding the task
            Toast.makeText(this, "Task added!", Toast.LENGTH_SHORT).show(); // Provide user feedback
        } else {
            // Show a Toast message if the input is empty
            Toast.makeText(this, "Please enter a task.", Toast.LENGTH_SHORT).show();
        }
    }
}
