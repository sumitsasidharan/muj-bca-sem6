import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editTextNewTask;
    Button buttonAddTask;
    ListView listViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editTextNewTask = findViewById(R.id.etNewTask);
        buttonAddTask = findViewById(R.id.btnAddTask);
        listViewTasks = findViewById(R.id.lvTasks);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask =
                    editTextNewTask.getText().toString();
                if (!newTask.isEmpty()) {
                    myDb.insertData(newTask);
                    populateListView();
                    editTextNewTask.setText("");
                }
            }
        });
        populateListView();
    }

    private void populateListView() {
        ArrayList < String > taskList = new ArrayList < > ();
        Cursor data = myDb.getAllData();
        while (data.moveToNext()) {
            // The 1 here is the index of the column in your database table
            taskList.add(data.getString(1));
        }
        ArrayAdapter < String > adapter = new ArrayAdapter < > (this, android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(adapter);
    }
}