package com.example.foodappprojet2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class searchName extends AppCompatActivity {
    Button btn;
    EditText  search;
    RecyclerView myRecyclerView;
    public static AppCompatActivity me;
    public AsynSearchByName task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);
        btn = findViewById(R.id.searchName);
        search = findViewById(R.id.editSearchByName);
        myRecyclerView = findViewById(R.id.recyclerViewSearchByName);
        me = this;
        btn.setOnClickListener(new View.OnClickListener() { // when We click to this button using the event that listens a redirection is made to the SearchName Activity
            @Override
            public void onClick(View v) {
                String test = search.getText().toString().toLowerCase();

                String url = "https://www.themealdb.com/api/json/v1/1/search.php?s="+test;
                new AsynSearchByName(me).execute(url);
                search.setText("");
            }

        });

    }
}