package com.ldgoyes.condorlabsskilltestgoyes.view.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.SummaryHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceListPresenterRVAdapter;

import java.util.HashMap;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.Holder> {

    private SummaryHolder[] data;
    private InterfaceListPresenterRVAdapter presenterList;
    private HashMap<String,Bitmap> imagesToShow;

    public AdapterRecyclerView(SummaryHolder[] data, InterfaceListPresenterRVAdapter presenterList) {
        this.data = data;
        this.presenterList = presenterList;
        this.imagesToShow = new HashMap<>();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public ImageView imageViewPoster;
        public TextView textViewTitle;
        public TextView textViewVoteAverage;

        public Holder(View itemView) {
            super(itemView);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.recyclerview_item_imageview);
            textViewTitle = (TextView) itemView.findViewById(R.id.recyclerview_item_title);
            textViewVoteAverage = (TextView) itemView.findViewById(R.id.recyclerview_item_voteaverage);
        }
    }

    public void addImageToShow( String movieId, Bitmap image ){
        imagesToShow.put( movieId, image );
    }

    public void clearImagesToShow(){
        imagesToShow = new HashMap<>();
    }

    @Override
    public AdapterRecyclerView.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerviewitem, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenterList.recyclerviewNotifyItemSelected( position );
            }
        });
        if( imagesToShow.containsKey( data[position].movieId ) ){
            Bitmap imageToShow = imagesToShow.get( data[position].movieId );
            holder.imageViewPoster.setImageBitmap( imageToShow );
        }
        holder.textViewTitle.setText( data[position].movieName );
        holder.textViewVoteAverage.setText( data[position].voteAverage );
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}