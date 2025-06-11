import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    GridView gridViewImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridViewImages = findViewById(R.id.gridViewImages);
        // Array of image resource IDs
        // Replace R.drawable.image1, R.drawable.image2, R.drawable.image3, etc., with the actual drawable resource IDs of your images.
        int[] imageIds = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3, // Add your images
            // More images...
        };
        // Set the adapter to the GridView
        gridViewImages.setAdapter(new ImageAdapter(this,
            imageIds));
    }
}