package com.example.trip_for_everyone;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderPage extends RecyclerView.ViewHolder {
    private TextView textView;
    private LinearLayout linearLayout;
    String data;
    public ViewHolderPage(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.pagerTextView);
        linearLayout = itemView.findViewById(R.id.lllayout);
    }

    public void onBind(String data){
        this.data = data;
        textView.setText(data.toString());
    }
}
