package com.satandigital.movieandtvinfo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.satandigital.movieandtvinfo.R;
import com.satandigital.movieandtvinfo.SearchMediaActivity;
import com.satandigital.movieandtvinfo.models.MediaObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    //Data
    private ArrayList<MediaObject> media;
    private String TMDB_BASE_POSTER_PATH;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movie_poster_iv;
        TextView movie_title_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            movie_poster_iv = itemView.findViewById(R.id.movie_poster_iv);
            movie_title_tv = itemView.findViewById(R.id.movie_title_tv);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MediaAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.media = new ArrayList<>();

        TMDB_BASE_POSTER_PATH = mContext.getResources().getString(R.string.TMDB_BASE_POSTER_PATH);
    }

    @Override
    public MediaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Picasso.get()
                .load(TMDB_BASE_POSTER_PATH + media.get(position).getPoster_path())
                .placeholder(R.drawable.placeholder_poster)
                .error(R.drawable.placeholder_error_poster)
                .into(holder.movie_poster_iv);
        holder.movie_title_tv.setText(media.get(position).getOriginal_name());

        holder.movie_poster_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SearchMediaActivity)mContext).selectedMedia(media.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return media.size();
    }

    public void setData(ArrayList<MediaObject> movies){
        this.media.clear();
        this.media.addAll(movies);
        notifyDataSetChanged();
    }
}
