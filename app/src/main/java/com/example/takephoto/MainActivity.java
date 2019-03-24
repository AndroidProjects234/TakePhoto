package com.example.takephoto;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE=1;   //Identify the intent
    ImageView buckysImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buckysButton=(Button)findViewById(R.id.buckysButton);//Reference to button
        buckysImageView=(ImageView)findViewById(R.id.buckysImageView);//Reference to imageview

        //disable the button if user has no camera
        if(!hasCamera())
            buckysButton.setEnabled(false);
    }

    //Check if user has camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
    public void launchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take a picture and pass along it to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);   //We need some information back after launching camera
    }
    //If you want to return image taken

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
       if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
           //Get the photo
           Bundle extras=data.getExtras();
           Bitmap photo=(Bitmap)extras.get("data");//give the information about photo
           buckysImageView.setImageBitmap(photo);   //Set the image to imageview
       }
    }
}
