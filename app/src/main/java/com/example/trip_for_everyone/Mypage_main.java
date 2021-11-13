package com.example.trip_for_everyone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Mypage_main extends AppCompatActivity {

    ImageView member_image;
    TextView member_res_text;
    TextView member_name_text;
    Button profile_edit_button;
    ImageView bookmark_image;
    ImageView fairtrip_img;
    TextView course_text;
    ImageView myreview_img;
    TextView fairtrip_text;
    TextView myreview_text;
    TextView bookmark_text;
    ImageView course_img;
    Button logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_main);

        member_image = (ImageView)findViewById(R.id.member_image);
        member_res_text = (TextView)findViewById(R.id.member_res_text);
        member_name_text = (TextView)findViewById(R.id.member_name_text);
        profile_edit_button = (Button)findViewById(R.id.profile_edit_button);
        bookmark_image = (ImageView)findViewById(R.id.bookmark_image);
        fairtrip_img = (ImageView)findViewById(R.id.fairtrip_img);
        course_text = (TextView)findViewById(R.id.course_text);
        myreview_img = (ImageView)findViewById(R.id.myreview_imag);
        fairtrip_text = (TextView)findViewById(R.id.fairtrip_text);
        myreview_text = (TextView)findViewById(R.id.myreview_text);
        bookmark_text = (TextView)findViewById(R.id.bookmark_text);
        course_img = (ImageView)findViewById(R.id.course_img);
        logout_button = (Button) findViewById(R.id.logout_button);
    }
}