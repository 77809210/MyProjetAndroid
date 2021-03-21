package com.example.foodappprojet2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class categories extends AppCompatActivity {

    DatabaseHelper mDtabaseHelper;
    ArrayList<Food> filteredList;
    EditText searchText;
    AsyncFoodJsonData task;
    private static final String url = "https://www.themealdb.com/api/json/v1/1/categories.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        searchText = findViewById(R.id.editSearch);
        this.mDtabaseHelper = new DatabaseHelper(this);
         task = new AsyncFoodJsonData(this,mDtabaseHelper);// there i call the asynchrone task witch get the list of url from MealDB server
        task.execute(url); // i execute the url

        searchText.addTextChangedListener(new TextWatcher() { // I link the Listener to my button. when the editText change , the method afterTextChanged is called
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {//when the editText change this method is called
                if(s.toString().isEmpty())
                {
                    RecyclerViewAdapter.mData= RecyclerViewAdapter.mData2; //then we set the dataset after that i notified the changing of my adapter
                    AsyncFoodJsonData.adapter1.notifyDataSetChanged();
                }
                else{
                filter(s.toString());
            }
            }
        });
    }
    private void filter(String str)
    {
        filteredList = new ArrayList<Food>();
        for(Food item: RecyclerViewAdapter.mData)
        {
            if(item.getName().toLowerCase().contains(str.toLowerCase()))
            {
                filteredList.add(item);
                RecyclerViewAdapter.dd(item.getImage());
            }
        }
        AsyncFoodJsonData.adapter1.filterList(filteredList);
    }
}