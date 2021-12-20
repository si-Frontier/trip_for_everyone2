package com.example.trip_for_everyone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class InfoActivity extends AppCompatActivity {
    ImageButton bookmark;
    boolean isBookmarked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        isBookmarked = false;

        bookmark = findViewById(R.id.info_bookmark);
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBookmarked){
                    bookmark.setColorFilter(getResources().getColor(R.color.black));
                    isBookmarked = false;
                }
                else {
                    isBookmarked = true;
                    bookmark.setColorFilter(getResources().getColor(R.color.real_yellow));
                }
            }
        });
    }
}
