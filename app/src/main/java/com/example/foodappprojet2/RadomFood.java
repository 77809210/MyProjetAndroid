package com.example.foodappprojet2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class RadomFood extends AppCompatActivity {
    AsyncRandomFood task;
    ImageView img;
    static String imageURL;
    AsyncBitmapDownloader task2;
    public static Bitmap bitmap;
    private static final String url = "https://www.themealdb.com/api/json/v1/1/random.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radom_food);
        img = findViewById(R.id.imageR);
        task = new AsyncRandomFood(this);// there i call the asynchrone task witch get the list of url from MealDB server
        task.execute(url); // i execute the url
         new AsyncBitmapDownloader().execute(imageURL);
         img.setImageBitmap(bitmap);

    }
}