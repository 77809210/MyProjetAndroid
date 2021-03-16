package com.example.foodappprojet2;

import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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
    MyAdapter adapter= null;
    private AppCompatActivity myActivity;
    DatabaseHelper mDatabaseHelper;

    public AsyncFoodJsonData(AppCompatActivity mainActivity, MyAdapter adp,DatabaseHelper mDatabaH) {
        this.myActivity = mainActivity;
        this.adapter = adp;
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
    protected void onPostExecute(JSONObject s) { //this function iterate over all images JsonArray contained in received JSONObject

        ListView list = (ListView)myActivity.findViewById(R.id.list);
        MyAdapter tableau = new MyAdapter(list.getContext());
        list.setAdapter(tableau);

        // For testing purpose
        //Bitmap largeIcon = BitmapFactory.decodeResource(myActivity.getResources(), R.drawable.test);
        //tableau.add(largeIcon);

        try {
            JSONArray item = s.getJSONArray("categories");
            for (int i = 0; i<item.length(); i++)
            {

                JSONObject fe = item.getJSONObject(i);
                String urlimage = fe.getString("strCategoryThumb");
                String categories = fe.getString("strCategory");
                String description = fe.getString("strCategoryDescription");
                this.mDatabaseHelper.addData(categories,urlimage,description);
                Log.i("JFL", "Adding to adapter url: " + urlimage);
                // Downloading image
                //AsyncBitmapDownloader abd = new AsyncBitmapDownloader(tableau);
              // abd.execute(urlimage);

            }
            Cursor data = mDatabaseHelper.getData();
            while(data.moveToNext())
            {
                AsyncBitmapDownloader abd = new AsyncBitmapDownloader(tableau);
                abd.execute(data.getString(2));
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

        // Extracting the JSON object from the String
        //String jsonextracted = sb.substring("jsonFlickrFeed(".length(), sb.length() - 1);
        //Log.i("CIO", jsonextracted);
        return sb.toString();
    }

}


