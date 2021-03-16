package com.example.foodappprojet2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

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
    ListView ListImage;
    ImageView imageView;
    DatabaseHelper mDtabaseHelper;
    private static final String url = "https://www.themealdb.com/api/json/v1/1/categories.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mDtabaseHelper = new DatabaseHelper(this);
        ListImage = findViewById(R.id.list);
        MyAdapter adapt = new MyAdapter(ListImage.getContext()); // there i link my adapteur with mys Listview
        ListImage.setAdapter(adapt);
        AsyncFoodJsonData task = new AsyncFoodJsonData(this,adapt,mDtabaseHelper);// there i call the asynchrone task witch get the list of url from Flickr server
       task.execute(url); // i execute the url
    }


}