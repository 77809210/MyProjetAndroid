package com.example.foodappprojet2;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Vector;

public class MyAdapter extends BaseAdapter { //this classnis a bridge between UI component and data source that helps us to fill data in IU component.
    private Context context = null;
    static Vector vector = new Vector<String>(); // this vector store url from that i get from JSONObject
    ArrayList<Bitmap> myList = new ArrayList<Bitmap>();
    LayoutInflater inflter;
    public MyAdapter(Context context)
    {
        this.context = context;
        inflter = (LayoutInflater.from(context));
    }

     // returns the number of objects present in our list
    @Override
    public int getCount() {
        return myList.size();
    }
    // return an element of our list according to its position
    @Override
    public Bitmap getItem(int position)
    {
        return myList.get(position);
    }
    //returns the id of an element of our list according to its position
    @Override
    public long getItemId(int position)
    {
        return myList.indexOf(getItem(position));
    }

    public void add(Bitmap image) {
        myList.add(image);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Bitmap image = (Bitmap)getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.uneimage, parent, false);
        }

        ImageView iv = (ImageView)convertView.findViewById(R.id.imageView);
        iv.setImageBitmap(image);

        return convertView;
    }
    public void  dd(String url) //this function adds received url in my vector
    {
        vector.add(url);
    }
}
