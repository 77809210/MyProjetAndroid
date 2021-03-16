package com.example.foodappprojet2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class AsyncBitmapDownloader extends AsyncTask<String, Void, Bitmap> {

    MyAdapter adapter_ = null;

    public AsyncBitmapDownloader(MyAdapter adapter) {
        adapter_ = adapter;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        Bitmap bm = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
            bm = BitmapFactory.decodeStream(in);
            in.close();
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        //Log.i("CIO", "Image received !");
        adapter_.add(bitmap);
        adapter_.notifyDataSetChanged();
    }
}
