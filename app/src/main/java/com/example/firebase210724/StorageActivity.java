package com.example.firebase210724;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StorageActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://fir-210724.appspot.com/");
        StorageReference storageRef = storage.getReference().child("2021.jpg");
        imageView = findViewById(R.id.imageView);

        downloadBitmap(storageRef, imageView);

    }

    private void downloadBitmap(StorageReference storageReference, ImageView imageView) {
        storageReference.getBytes(1024 * 1024).addOnSuccessListener(bytes -> {
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imageView.setImageBitmap(bmp);
        }).addOnFailureListener(bytes -> Toast.makeText(getApplicationContext(), "failed to load image", Toast.LENGTH_SHORT).show());
    }


}
