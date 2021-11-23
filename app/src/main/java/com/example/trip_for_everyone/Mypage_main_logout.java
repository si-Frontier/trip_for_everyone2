package com.example.trip_for_everyone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Mypage_main_logout extends AppCompatActivity {

    TextView mypage_etc_logout;
    TextView mypage_service_guide_logout;
    TextView mypage_account_logout;
    TextView myapge_app_setting_logout;
    ImageView member_image_logout;
    TextView member_res_text_logout;
    TextView member_name_text_logout;
    Button profile_edit_button_logout;
    Button mypage_login_button;
    Button course_text_logout;
    Button alarm_logout;
    Button myreview_text_logout;
    Button bookmark_text_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_main_logout);

        mypage_etc_logout = (TextView) findViewById(R.id.mypage_etc_logout);
        mypage_service_guide_logout = (TextView) findViewById(R.id.mypage_service_guide_logout);
        mypage_account_logout = (TextView) findViewById(R.id.mypage_account_logout);
        myapge_app_setting_logout = (TextView) findViewById(R.id.myapge_app_setting_logout);
        member_image_logout = (ImageView) findViewById(R.id.member_image_logout);
        member_res_text_logout = (TextView) findViewById(R.id.member_res_text_logout);
        member_name_text_logout = (TextView) findViewById(R.id.member_name_text_logout);
        profile_edit_button_logout = (Button) findViewById(R.id.profile_edit_button_logout);
        mypage_login_button = (Button) findViewById(R.id.mypage_login_button);
        course_text_logout = (Button) findViewById(R.id.course_text_logout);
        alarm_logout = (Button) findViewById(R.id.alarm_logout);
        myreview_text_logout = (Button) findViewById(R.id.myreview_text_logout);
        bookmark_text_logout = (Button) findViewById(R.id.bookmark_text_logout);
    }
}