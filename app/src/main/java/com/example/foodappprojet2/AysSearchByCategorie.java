package com.example.foodappprojet2;

import android.os.AsyncTask;
import android.util.Log;
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

public class AysSearchByCategorie extends AsyncTask<String, Void, JSONObject> {

    public static JSONObject json;
    public RecyclerViewAdapterIng adapter;
    private AppCompatActivity myActivity;
    private RecyclerView myRecyclerView;

    public AysSearchByCategorie(AppCompatActivity mainActivity) {
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
        RecyclerViewAdapterIng.mData.clear(); //there i clean my dataset for a new request
        if(s.length() == 0) //if the result of resquest is null i return a toast else i do postWork
        {
            CharSequence text = "this Categories doesnt exist in our Database please check or change";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(myActivity, text, duration);
            toast.show();
        }
        else {
            try {
                JSONArray item = s.getJSONArray("meals");
                for (int i = 0; i < item.length(); i++) //i foreach the result of my Json object in order to to fill my list
                {
                    JSONObject fe = item.getJSONObject(i);
                    String urlimage = fe.getString("strMealThumb"); // i get the url of image
                    String name = fe.getString("strMeal");
                    resultIngredientSearch rslt = new resultIngredientSearch(name, urlimage);
                    RecyclerViewAdapterIng.mData.add(rslt);
                }
                myRecyclerView = (RecyclerView) myActivity.findViewById(R.id.recyclerViewSearchByIngredient);
                adapter = new RecyclerViewAdapterIng(RecyclerViewAdapterIng.mData, myActivity);
                myRecyclerView.setLayoutManager(new GridLayoutManager(myActivity, 1));
                myRecyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
