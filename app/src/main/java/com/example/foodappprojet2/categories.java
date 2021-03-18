package com.example.foodappprojet2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class categories extends AppCompatActivity {

    DatabaseHelper mDtabaseHelper;
    RecyclerViewAdapter myAdapter;
    private static final String url = "https://www.themealdb.com/api/json/v1/1/categories.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        this.mDtabaseHelper = new DatabaseHelper(this);
        AsyncFoodJsonData task = new AsyncFoodJsonData(this,mDtabaseHelper);// there i call the asynchrone task witch get the list of url from MealDB server
        task.execute(url); // i execute the url
    }
}