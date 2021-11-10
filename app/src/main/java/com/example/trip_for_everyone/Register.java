package com.example.trip_for_everyone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;


//회원가입
public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;

    //local_check에서 확인한 주소값 가져오기
    // final static Local_check localCheck = new Local_check();
    // private static String address = localCheck.address;
    public String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage.getInstance();
        Button local_check = (Button) findViewById(R.id.local_check);
        findViewById(R.id.check).setOnClickListener(onClickListener);

        local_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Local_check.class);
                startActivityForResult(intent,1000);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            //Local_check.java 주소값 가져와서 저장
            address = data.getStringExtra("address");
            //Toast.makeText(Register.this, "Result: " + data.getStringExtra("address"), Toast.LENGTH_SHORT).show();
        } else {   // RESULT_CANCEL
            Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
        }

    }





    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.check:
                    signUp();
                    break;
            }
        }
    };




    private  void signUp(){

        String name = ((EditText)findViewById(R.id.user_name)).getText().toString();
        String id = ((EditText)findViewById(R.id.user_id)).getText().toString();
        String password = ((EditText)findViewById(R.id.user_password)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.user_password_check)).getText().toString();




        if(id.length()>0&& password.length()>0&& passwordCheck.length()>0){
            if(password.equals(passwordCheck)){
                mAuth.createUserWithEmailAndPassword(id, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            final String uid = task.getResult().getUser().getUid();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //UserRegister userRegister = new UserRegister(name, address);
                            //mDatauser.child("users").child(id).setValue(userRegister);
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //요청 메일 전송이 완료되면 해당 매소드 발생
                                    UserRegister userRegister = new UserRegister();

                                    userRegister.userName = name;
                                    userRegister.uid = uid;
                                    userRegister.address = address;

                                    //database 저장
                                    mDatabase.getReference().child("users").child(uid).setValue(userRegister);
                                    Toast.makeText(Register.this, "메일발송완료!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);

                                }
                            });
                            Toast.makeText(Register.this, "회원가입 성공했습니다", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(task.getException().toString() !=null){
                                Toast.makeText(Register.this, "회원가입 실패했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

            }
            else{
                Toast.makeText(Register.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(Register.this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
        }
    }



}