package com.niksan.recyclerviewretrofitglide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<Product> products;
    Context context;

    public RecyclerAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.color.setText(products.get(position).getColor());
        holder.name.setText(products.get(position).getProductname());
        holder.price.setText(products.get(position).getPrice());
        Glide.with(context).load(products.get(position).getImageurl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, color,price;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.productimage);
            name = (TextView) itemView.findViewById(R.id.name);
            color = (TextView) itemView.findViewById(R.id.color);
            price= (TextView) itemView.findViewById(R.id.price);

        }
    }
}
