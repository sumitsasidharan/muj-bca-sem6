package com.example.todolistapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast; // Import Toast for displaying messages
import androidx.annotation.NonNull;
import java.util.List;

/**
 * Custom ArrayAdapter for displaying tasks in a ListView.
 * This adapter uses a custom layout (list_item.xml) for each row
 * and handles the delete button click for each task.
 */
public class TaskAdapter extends ArrayAdapter<String> {

    // Layout resource ID for each list item
    private int resourceLayout;
    // Context from the calling activity
    private Context mContext;
    // List of tasks to be displayed
    private List<String> tasksList;

    /**
     * Constructor for the TaskAdapter.
     * @param context The current context (e.g., MainActivity.this).
     * @param resource The resource ID for a layout file containing a TextView to use when instantiating views.
     * @param items The list of task strings to display.
     */
    public TaskAdapter(@NonNull Context context, int resource, @NonNull List<String> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
        this.tasksList = items; // Store the reference to the original list
    }

    /**
     * Provides a View for an AdapterView (ListView, GridView, etc.)
     * @param position The position of the item within the adapter's data set.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        // If the view is null, inflate it from the custom list_item layout
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, parent, false); // Use parent and attachToRoot=false
        }

        // Get the task string for the current position
        final String task = getItem(position);

        if (task != null) {
            // Find the TextView and Button within the inflated view
            TextView textViewTaskDescription = v.findViewById(R.id.textViewTaskDescription);
            Button buttonDeleteTask = v.findViewById(R.id.buttonDeleteTask);

            // Set the task description text
            if (textViewTaskDescription != null) {
                textViewTaskDescription.setText(task);
            }

            // Set OnClickListener for the delete button
            if (buttonDeleteTask != null) {
                buttonDeleteTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Remove the task from the list and notify the adapter
                        // It's safer to remove using the stored list reference
                        if (tasksList.contains(task)) { // Check if task still exists before removing
                            tasksList.remove(task);
                            notifyDataSetChanged(); // Refresh the ListView to reflect the change
                            Toast.makeText(mContext, "Task deleted: " + task, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
        return v;
    }
}
