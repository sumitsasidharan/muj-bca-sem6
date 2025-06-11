package com.yourpackage.name; // Replace with your actual package name
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewCaptured;
    private String currentPhotoPath;
    private final ActivityResultLauncher < Intent >
        takePictureActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result - > {
                if (result.getResultCode() == RESULT_OK) {
                    setPic();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonCapture =
            findViewById(R.id.buttonCapture);
        imageViewCaptured =
            findViewById(R.id.imageViewCaptured);
        buttonCapture.setOnClickListener(view - >
            dispatchTakePictureIntent());
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new
        SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir =
            getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new
        Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI =
                    FileProvider.getUriForFile(this,
                        "com.yourpackage.name.fileprovider",
                        // Update with your package name and applicationId +
                        .fileprovider photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                takePictureActivityResultLauncher.launch(takePictureIntent);
            }
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageViewCaptured.getWidth();
        int targetH = imageViewCaptured.getHeight();
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new
        BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));
        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Bitmap bitmap =
            BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageViewCaptured.setImageBitmap(bitmap);
    }
}