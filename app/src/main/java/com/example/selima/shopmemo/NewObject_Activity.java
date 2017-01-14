package com.example.selima.shopmemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewObject_Activity extends AppCompatActivity {

    final int PHOTO_REQUEST_CODE = 1;
    boolean take = false;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_object);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);

        ab.setTitle("Nuovo oggetto");

        ImageView takephoto = (ImageView)findViewById(R.id.takephoto);
        takephoto.setImageResource(R.drawable.ic_camera);
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PHOTO_REQUEST_CODE)
        {
            if(data!=null) {
                photo = (Bitmap) data.getExtras().get("data");
                if (photo != null) {
                    TextView scatta = (TextView) findViewById(R.id.scatta);
                    scatta.setVisibility(View.INVISIBLE);
                    ImageView takephoto = (ImageView) findViewById(R.id.takephoto);
                    takephoto.setImageResource(0);
                    takephoto.setImageBitmap(photo);
                }
            }
        }
    }
}
