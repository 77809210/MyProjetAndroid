package com.example.foodappprojet2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.app.SearchManager;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CardView Cardcategories;
    CardView Cardfood;
    CardView Cardingredient;
    CardView Cardsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cardcategories = findViewById(R.id.categories);
        Cardfood = findViewById(R.id.food);
        Cardingredient = findViewById(R.id.ingredient);
        Cardsearch = findViewById(R.id.search);

        Cardcategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities();
            }
        });

    }
    private void showToast(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    private void switchActivities()// this function allows the start of ListActivity
    {
        Intent switchActivittIntent = new Intent(this,categories.class);
        startActivity(switchActivittIntent);
    }
}
