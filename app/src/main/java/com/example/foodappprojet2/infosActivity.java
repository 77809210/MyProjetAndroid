package com.example.foodappprojet2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class infosActivity extends AppCompatActivity {
    private TextView nameRecipe;
    private TextView ingredient;
    private TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
        nameRecipe = (TextView)findViewById(R.id.named);
        ingredient = (TextView)findViewById(R.id.ingredients);
        description = (TextView)findViewById(R.id.description);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("RecipeName");
        String Ingredients = intent.getExtras().getString("Ingredients");
        String Description = intent.getExtras().getString("descriptions");

        nameRecipe.setText(Title);
        ingredient.setText(Ingredients);
        description.setText(Description);
    }
}