package com.example.trip_for_everyone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class mypage_course extends AppCompatActivity {

    TextView textView4;
    ImageView imageView;
    ImageView imageView7;
    Button button3;
    Button button4;
    Button button5;
    ImageView imageView13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_course);

        textView4 = (TextView) findViewById(R.id.textView4);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        imageView13 = (ImageView) findViewById(R.id.imageView13);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mypage_course.this, mypage_course_start.class);
                startActivity(intent);
            }
        });
    }

}


