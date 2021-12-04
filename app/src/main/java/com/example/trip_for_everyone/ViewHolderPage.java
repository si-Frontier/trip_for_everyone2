package com.example.trip_for_everyone;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderPage extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private LinearLayout linearLayout;
    String data;
    public ViewHolderPage(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.recommandImage1);
        linearLayout = itemView.findViewById(R.id.lllayout);
    }

    public void onBind(String path){
        this.data = data;
        StorageDownload download = new StorageDownload();
        download.download(path, imageView, imageView.getContext());
       // imageView.setText(data.toString());
    }
}
