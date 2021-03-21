package com.example.foodappprojet2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    private Context mContext;
    public static  ArrayList<Bitmap> myList = new ArrayList<Bitmap>();
    public static ArrayList<Food> mData = new ArrayList<>();
    public static ArrayList<Food> mData2 = new ArrayList<>();
    public static List<String> listURL = new ArrayList<>();


    public RecyclerViewAdapter(Context context,ArrayList<Food> foodList)
    {
        this.mContext = context;
        this.mData = foodList;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_infos,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyHolder holder, int position) {
        Picasso.with(mContext).load(mData.get(position).getImage()).into(holder.imageView);
        holder.name.setText(mData.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,infosActivity.class);
                intent.putExtra("Name",mData.get(position).getName());
                intent.putExtra("ImageD",mData.get(position).getImage());
                intent.putExtra("descriptions",mData.get(position).getDescription());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }
    //the ViewHolder get out of scope(screen)
    public class MyHolder extends RecyclerView.ViewHolder { //this method is hepful to link my recyclerViewAdapter with my cardiew_infos.xml

        TextView name;
        CardView cardView;
        ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.textI);
            cardView = (CardView)itemView.findViewById(R.id.cardcontent);
            imageView = (ImageView)itemView.findViewById(R.id.imageV);
        }
    }
    public void add(Bitmap image) {
        myList.add(image);
    }
    public static void dd(String url)
    {
        listURL.add(url);
    }

    public void filterList(ArrayList<Food> filteredList)
    {
        mData = filteredList;
        this.notifyDataSetChanged();
    }
}
