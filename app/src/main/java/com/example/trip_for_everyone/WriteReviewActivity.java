package com.example.trip_for_everyone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class WriteReviewActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private Button button;
    private String reviewNum;
    private String uid;
    private String content;
    private EditText editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        button = findViewById(R.id.button);
        editContent =findViewById(R.id.content);

        //리뷰 등록
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //사용자 uid 받아오기
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid="";

                // content 받아오기
                content = editContent.getText().toString();

                // 로그인하지 않았을 때 경고창
                if(user == null)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WriteReviewActivity.this);
                            builder.setTitle("알림");
                    builder.setMessage("로그인 후 이용해주세요");
                    builder.setPositiveButton("확인",null);
                    builder.create().show();
                }

                // content 공백일 때 경고창
                else if ( editContent.getText().toString().length() == 0 ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WriteReviewActivity.this);
                    builder.setTitle("알림");
                    builder.setMessage("리뷰를 작성해주세요");
                    builder.setPositiveButton("확인",null);
                    builder.create().show();
                }
                // 로그인 O + 공백 아닐 때
                else {
                    uid = user.getUid();
                    //public info_review(int uid, String name, String content,nowtime)
                    //데이터 베이스 쓰기
                    info_review review = new info_review(uid,"soyoung",content,System.currentTimeMillis());
                    mDatabase.child("place").child("맥도날드")./*장소 받아오기*/child("review").child("review1").setValue(review);
                }


            }
        });

    }




    //리뷰 데이터베이스 클래스
    public class info_review {

        public String uid;
        public String content;
        public String name;
        public long nowTime;

        public info_review() {
            // Default constructor
        }

        public info_review(String uid, String name, String content,long nowTime) {
            this.uid = uid;
            this.content = content;
            this.name = name;
            this.nowTime = nowTime;
        }
    }





}