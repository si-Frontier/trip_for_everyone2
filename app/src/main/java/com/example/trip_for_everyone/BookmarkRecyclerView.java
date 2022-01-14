package com.example.trip_for_everyone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookmarkRecyclerView extends RecyclerView.Adapter<BookmarkRecyclerView.ViewHolder> {
    private ArrayList<String> bookmarkList;
    @NonNull
    @Override
    public BookmarkRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkRecyclerView.ViewHolder holder, int position) {
        holder.onBind(bookmarkList.get(position));
    }
    public void setBookmarkList(ArrayList<String> list){
        this.bookmarkList = list;

    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.bookmark_thumbnail);
            textView = itemView.findViewById(R.id.bookmark_title);
        }
        void onBind(String title){
            StorageDownload download = new StorageDownload();
            download.download("spot/"+title,thumbnail,thumbnail.getContext());
            textView.setText(title);
        }
    }
}
