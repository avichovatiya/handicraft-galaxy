package com.example.pankajknitting.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pankajknitting.R;
import com.example.pankajknitting.model.recyclerModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<recyclerModel> recyclerModelArrayList;

    //Constructor
    public recyclerAdapter(Context context, ArrayList<recyclerModel> recyclerModelArrayList) {
        this.context = context;
        this.recyclerModelArrayList = recyclerModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        recyclerModel model = recyclerModelArrayList.get(position);
//        holder.img.setImageResource(model.getImg());
        Picasso.get().load(model.getImg()).into(holder.img);
        holder.desc.setText("" + model.getDesc());
        holder.price.setText("Rs. " + model.getPrice());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return recyclerModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final TextView desc;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.p_img);
            desc = itemView.findViewById(R.id.desc);
            price = itemView.findViewById(R.id.price);
        }
    }
}
