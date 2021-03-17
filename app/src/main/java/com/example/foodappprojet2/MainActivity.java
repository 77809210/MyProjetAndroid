package com.example.foodappprojet2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.app.SearchManager;
import android.widget.SearchView.OnQueryTextListener;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDtabaseHelper;
    RecyclerViewAdapter myAdapter;
    private static final String url = "https://www.themealdb.com/api/json/v1/1/categories.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mDtabaseHelper = new DatabaseHelper(this);
        AsyncFoodJsonData task = new AsyncFoodJsonData(this,mDtabaseHelper);// there i call the asynchrone task witch get the list of url from MealDB server
        task.execute(url); // i execute the url
    }


}