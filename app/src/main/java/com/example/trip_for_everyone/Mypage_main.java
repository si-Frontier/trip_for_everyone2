package com.example.trip_for_everyone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Mypage_main extends AppCompatActivity {

    TextView mypage_etc;
    TextView mypage_service_guide;
    TextView mypage_account;
    TextView myapge_app_setting;
    ImageView member_image;
    TextView member_res_text;
    TextView member_name_text;
    Button profile_edit_button;
    Button mypage_logout_button;
    Button course_text;
    Button alarm;
    Button myreview_text;
    Button bookmark_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_main);

        mypage_etc = (TextView)findViewById(R.id.mypage_etc);
        mypage_service_guide = (TextView)findViewById(R.id.mypage_service_guide);
        mypage_account = (TextView)findViewById(R.id.mypage_account);
        myapge_app_setting = (TextView)findViewById(R.id.myapge_app_setting);
        profile_edit_button = (Button)findViewById(R.id.profile_edit_button);
        member_image = (ImageView)findViewById(R.id.member_image);
        member_res_text = (TextView)findViewById(R.id.member_res_text);
        member_name_text = (TextView)findViewById(R.id.member_name_text);
        profile_edit_button = (Button) findViewById(R.id.profile_edit_button);
        mypage_logout_button = (Button) findViewById(R.id.mypage_logout_button);
        course_text = (Button) findViewById(R.id.course_text);
        alarm = (Button) findViewById(R.id.alarm);
        myreview_text = (Button) findViewById(R.id.myreview_text);
        bookmark_text = (Button) findViewById(R.id.bookmark_text);



        mypage_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Mypage_main_logout.class);
                startActivity(intent);
            }
        });
    }





}