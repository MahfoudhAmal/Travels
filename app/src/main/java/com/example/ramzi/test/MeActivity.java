package com.example.ramzi.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MeActivity extends AppCompatActivity {
     private final String TAG = this.getClass().getName();
    ImageView profilImage;
    ImageView ivGallerie,ivCamera;

    GalleryPhoto galleryPhoto ;
    CameraPhoto cameraPhoto;
    final int CAMERA_REQUEST = 13323;
    final int GALLERY_REQUEST = 23451;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);



        Bundle bundle = getIntent().getExtras();
        TextView user = (TextView)findViewById(R.id.user);
        TextView email = (TextView)findViewById(R.id.email);
        String use = bundle.getString("user");
        String mail = bundle.getString("email");
        user.setText(use);
        email.setText(mail);



        Button home = (Button) findViewById(R.id.home);
        Button trips = (Button) findViewById(R.id.trips);
        Button alert = (Button) findViewById(R.id.alert);
        Button me = (Button) findViewById(R.id.me);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent int0 = new Intent(MeActivity.this,ProfileActivity.class);
                startActivity(int0);
            }
        });

        profilImage = (ImageView)findViewById(R.id.profilImage);
        ivGallerie = (ImageView)findViewById(R.id.ivGallery);
        ivCamera = (ImageView)findViewById(R.id.ivCamera);

        galleryPhoto = new GalleryPhoto(getApplicationContext());
        cameraPhoto = new CameraPhoto(getApplicationContext());

        ivGallerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(galleryPhoto.openGalleryIntent(),GALLERY_REQUEST);
            }
        });
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERA_REQUEST);
                } catch (IOException e) {

                    Toast.makeText(getApplicationContext(),"something wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK)
        {
            if (requestCode == CAMERA_REQUEST)
            {
                String photoPath = cameraPhoto.getPhotoPath();
                try
                {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(100,100).getBitmap();
                    profilImage.setImageBitmap(bitmap);

                }catch (FileNotFoundException e)
                {
                    Toast.makeText(getApplicationContext(),"something wrong",Toast.LENGTH_SHORT).show();
                }
            }
            else if (requestCode == GALLERY_REQUEST)
            {
                String photoPath = galleryPhoto.getPath();
                try
                {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(100,100).getBitmap();
                    profilImage.setImageBitmap(bitmap);

                }catch (FileNotFoundException e)
                {
                    Toast.makeText(getApplicationContext(),"something wrong",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

}
