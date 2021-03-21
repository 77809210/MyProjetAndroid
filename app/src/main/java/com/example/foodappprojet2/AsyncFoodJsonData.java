package com.example.foodappprojet2;

import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

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

public class AsyncFoodJsonData extends AsyncTask<String, Void, JSONObject> {

    public static JSONObject json;
    RecyclerViewAdapter adapter;
    public static RecyclerViewAdapter adapter1;
    private AppCompatActivity myActivity;
    private RecyclerView myRecyclerView;
    DatabaseHelper mDatabaseHelper;

    public AsyncFoodJsonData(AppCompatActivity mainActivity,DatabaseHelper mDatabaH) {
        this.myActivity = mainActivity;
        this.mDatabaseHelper = mDatabaH;
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
            Log.e("result ",result);
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
        RecyclerViewAdapter.mData.clear();
        try {
            JSONArray item = s.getJSONArray("categories");
            for (int i = 0; i<item.length(); i++) //i foreach the result of my Json object in order to to fill my list
            {
                JSONObject fe = item.getJSONObject(i);
                String urlimage = fe.getString("strCategoryThumb"); // i get the url of image
                String categories = fe.getString("strCategory");// i get categorie
                String description = fe.getString("strCategoryDescription");
                this.mDatabaseHelper.addData(categories,urlimage,description);//there i add data to the table food_table of my database
                Food food = new Food(categories,urlimage,description);
                RecyclerViewAdapter.mData.add(food);
                RecyclerViewAdapter.mData2.add(food);
            }
            myRecyclerView = (RecyclerView) myActivity.findViewById(R.id.recyclerViewF);//there i get the instance of my recyclerView
            myRecyclerView.setHasFixedSize(true);
            adapter = new RecyclerViewAdapter(myActivity,RecyclerViewAdapter.mData);
            adapter1 = new RecyclerViewAdapter(myActivity,RecyclerViewAdapter.mData);//i link my main activity with my adapter
            myRecyclerView.setLayoutManager(new GridLayoutManager(myActivity,1));/*layout manager is responsible for letting the recyclerView know when
            to recycle a child view once it's gone out of scope*/
            myRecyclerView.setAdapter(adapter); //there i link my adapter with recyclerView in order to bind datasets to views that be displayed in the windows
            myRecyclerView.setAdapter(adapter1);
            Cursor data = mDatabaseHelper.getData();
            while(data.moveToNext())//i foreach my table food in order to recover url image and add it in list
            {
                RecyclerViewAdapter.dd(data.getString(2));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

}


