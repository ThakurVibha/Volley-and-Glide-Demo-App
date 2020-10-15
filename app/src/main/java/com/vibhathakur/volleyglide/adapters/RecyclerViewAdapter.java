package com.vibhathakur.volleyglide.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vibhathakur.volleyglide.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.vibhathakur.volleyglide.R;
import com.vibhathakur.volleyglide.activities.AnimeActivity;
import com.vibhathakur.volleyglide.model.Anim;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private Context mContext ;
    private List<Anim> mData ;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Anim> mData) {
        this.mContext = mContext;
        this.mData = mData;
        //Request option fo glide

        option=new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.anime_row_item,parent,false);
        final MyViewHolder viewHolder=new MyViewHolder(view);
        // click listener here
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext, AnimeActivity.class);
                i.putExtra("anime_name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("anime_description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("anime_studio",mData.get(viewHolder.getAdapterPosition()).getStudio());
                i.putExtra("anime_categorie",mData.get(viewHolder.getAdapterPosition()).getCategorie());
                i.putExtra("anime_episode",mData.get(viewHolder.getAdapterPosition()).getNb_episode());
                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImage_url());
                i.putExtra("anime_rating",mData.get(viewHolder.getAdapterPosition()).getRating());
                mContext.startActivity(i);
            }
        });
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_rating.setText(mData.get(position).getRating());
        holder.tv_studio.setText(mData.get(position).getStudio());
        holder.tv_category.setText(mData.get(position).getCategorie());

        // load image from the internet using Glide

        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.AnimeThumbnail);
        Glide.with(mContext)
                .load("https://cdn.myanimelist.net/images/anime/11/33657.jpg")
                .into(holder.AnimeThumbnail);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name,tv_rating,tv_studio,tv_category;
        ImageView AnimeThumbnail;
        LinearLayout view_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view_container=itemView.findViewById(R.id.container);
            tv_name=itemView.findViewById(R.id.anim_name);
            tv_category=itemView.findViewById(R.id.categorie);
            tv_rating=itemView.findViewById(R.id.rating);
            tv_studio=itemView.findViewById(R.id.studio);
            AnimeThumbnail=itemView.findViewById(R.id.thumbnail);

        }
    }
}
