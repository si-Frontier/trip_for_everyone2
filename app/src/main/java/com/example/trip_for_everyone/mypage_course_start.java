package com.example.trip_for_everyone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class mypage_course_start extends AppCompatActivity {

    TextView textView4;
    ImageView imageView;
    ImageView imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_course_start);

        textView4 = (TextView)findViewById(R.id.textView4);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
    }
}