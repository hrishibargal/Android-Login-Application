package com.hrishi.firstlogin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.TextImageHolder> {
    List<Body> list;

    public ListAdapter(List<Body> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TextImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items,parent,false);
        return new TextImageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TextImageHolder holder, int position) {
    Body body=list.get(position);
    holder.textView.setText(body.getTitle());
    Picasso.get().load("http://192.168.43.216:8000"+body.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TextImageHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public TextImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
