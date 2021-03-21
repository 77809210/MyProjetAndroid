package com.example.foodappprojet2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncRandomFood extends AsyncTask<String, Void, JSONObject> {

    public static JSONObject json;
    ImageView image;
    private AppCompatActivity myActivity;

    public AsyncRandomFood(AppCompatActivity mainActivity) {
        this.myActivity = mainActivity;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream

            result = readStream(in); // Read stream
            Log.e("result ", result);
            String r = result.toString();//i convert the final result to string
            json = null;
            json = new JSONObject(r);// i convert the result to object json to extract url easily to onPostExecute
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return json; // returns the result
    }

    @Override
    protected void onPostExecute(JSONObject s) {
        TextView  nameR = (TextView) myActivity.findViewById(R.id.nom);
        TextView  areaT = (TextView) myActivity.findViewById(R.id.area);
        TextView  categorieR = (TextView) myActivity.findViewById(R.id.categoriesR);
        image = (ImageView) myActivity.findViewById(R.id.imageR);
        TextView  ingredientR1 = (TextView) myActivity.findViewById(R.id.ingredient1);
        TextView  ingredientR2 = (TextView) myActivity.findViewById(R.id.ingredient2);
        TextView  ingredientR3 = (TextView) myActivity.findViewById(R.id.ingredient3);
        TextView  ingredientR4 = (TextView) myActivity.findViewById(R.id.ingredient4);
        TextView  ingredientR5 = (TextView) myActivity.findViewById(R.id.ingredient5);

        try {
            JSONArray item = s.getJSONArray("meals");
            for (int i = 0; i < item.length(); i++) //i foreach the result of my Json object in order to to fill my list
            {
                JSONObject fe = item.getJSONObject(i);
                String name = fe.getString("strMeal"); // i get the name of radom food
                String categories = fe.getString("strCategory");// i get categorie
                String area = fe.getString("strArea"); //the area
                RadomFood.imageURL = fe.getString("strMealThumb"); // i get the url of image
                String ingredient1 = fe.getString("strIngredient1");// i get ingredient1
                String ingredient2 = fe.getString("strIngredient2");
                String ingredient3 = fe.getString("strIngredient3");
                String ingredient4 = fe.getString("strIngredient4");
                String ingredient5 = fe.getString("strIngredient5");

                nameR.setText(name);
                categorieR.setText(categories);
                ingredientR1.setText(ingredient1);
                ingredientR2.setText(ingredient2);
                ingredientR3.setText(ingredient3);
                ingredientR4.setText(ingredient4);
                ingredientR5.setText(ingredient5);
                areaT.setText(area);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
