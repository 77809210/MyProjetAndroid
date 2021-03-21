package com.example.foodappprojet2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodInfos extends AppCompatActivity {
    private TextView name;
    private ImageView imageURL;
    private TextView areaT;
    private TextView linkYoutube;
    private TextView categorie;
    private TextView instruction;
    private TextView indregient1;
    private TextView indregient2;
    private TextView indregient3;
    private TextView indregient4;
    private TextView indregient5;
    private TextView indregient6;
    private TextView indregient7;
    private TextView indregient8;
    private TextView indregient9;
    private TextView indregient10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_infos);
        Bitmap bitmap;
        name = (TextView)findViewById(R.id.nameSI);
        imageURL = (ImageView)findViewById(R.id.imageSI);
        areaT = (TextView)findViewById(R.id.strarea);
        linkYoutube = (TextView)findViewById(R.id.link);
        categorie = (TextView)findViewById(R.id.categorieI);
        instruction = (TextView)findViewById(R.id.instructions);
        indregient1 = (TextView)findViewById(R.id.ingredient1);
        indregient2 = (TextView)findViewById(R.id.ingredient2);
        indregient3 = (TextView)findViewById(R.id.ingredient3);
        indregient4 = (TextView)findViewById(R.id.ingredient4);
        indregient5 = (TextView)findViewById(R.id.ingredient5);
        indregient6 = (TextView)findViewById(R.id.ingredient6);
        indregient7 = (TextView)findViewById(R.id.ingredient7);
        indregient8 = (TextView)findViewById(R.id.ingredient8);
        indregient9 = (TextView)findViewById(R.id.ingredient9);
        indregient10 = (TextView)findViewById(R.id.ingredient10);

        Intent intent = getIntent();
        String nameI = intent.getExtras().getString("Name");
        String Imageurl = intent.getExtras().getString("ImageD");
        String categories = intent.getExtras().getString("categories");
        String area = intent.getExtras().getString("area");
        String linkY = intent.getExtras().getString("linkY");
        String instructions = intent.getExtras().getString("instructions");
        String ing1 = intent.getExtras().getString("ingredient1");
        String ingr2 = intent.getExtras().getString("ingredient2");
        String ingr3 = intent.getExtras().getString("ingredient3");
        String ingr4 = intent.getExtras().getString("ingredient4");
        String ingr5 = intent.getExtras().getString("ingredient5");
        String ingr6 = intent.getExtras().getString("ingredient6");
        String ingr7 = intent.getExtras().getString("ingredient7");
        String ingr8 = intent.getExtras().getString("ingredient8");
        String ingr9 = intent.getExtras().getString("ingredient9");
        String ingr10 = intent.getExtras().getString("ingredient10");

        name.setText(nameI);
        AsyncBitmapDownloader asyc =  new AsyncBitmapDownloader();
        bitmap = asyc.doInBackground(Imageurl);
        imageURL.setImageBitmap(bitmap);
        areaT.setText(area);
        categorie.setText(categories);
        linkYoutube.setText(linkY);
        instruction.setText(instructions);
        indregient1.setText(ing1);
        indregient2.setText(ingr2);
        indregient3.setText(ingr3);
        indregient4.setText(ingr4);
        indregient5.setText(ingr5);
        indregient6.setText(ingr6);
        indregient7.setText(ingr7);
        indregient8.setText(ingr7);
        indregient9.setText(ingr9);
        indregient10.setText(ingr10);

    }
}