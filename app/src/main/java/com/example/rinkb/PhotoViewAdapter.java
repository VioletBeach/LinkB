package com.example.rinkb;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhotoViewAdapter extends RecyclerView.Adapter<PhotoViewAdapter.MyViewHolder> {
    private Context context;
    private List<String> list;

    public PhotoViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photo_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int index = position % list.size();
        String item = list.get(index);
        switch(index){
            case 0:
                holder.imgBanner.setBackgroundColor(Color.YELLOW);
                break;
            case 1:
                holder.imgBanner.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                holder.imgBanner.setBackgroundColor(Color.RED);
                break;
            case 3:
                holder.imgBanner.setBackgroundColor(Color.MAGENTA);
                break;
            case 4:
                holder.imgBanner.setBackgroundColor(Color.BLUE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBanner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner = itemView.findViewById(R.id.imgBanner);
        }
    }
}


