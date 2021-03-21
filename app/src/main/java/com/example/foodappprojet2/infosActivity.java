package com.example.foodappprojet2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class infosActivity extends AppCompatActivity {
    private TextView name;
    private ImageView imageURL;
    private TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bitmap bitmap;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
        name = (TextView)findViewById(R.id.named);
        imageURL = (ImageView)findViewById(R.id.imageD);
        description = (TextView)findViewById(R.id.description);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Name");
        String Images = intent.getExtras().getString("ImageD");
        String Description = intent.getExtras().getString("descriptions");

        name.setText(Title);
        AsyncBitmapDownloader asyc =  new AsyncBitmapDownloader(AsyncFoodJsonData.adapter1);
        bitmap = asyc.doInBackground(Images);
        imageURL.setImageBitmap(bitmap);
        description.setText(Description);
    }
}