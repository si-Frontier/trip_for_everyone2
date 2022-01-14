package com.example.trip_for_everyone;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
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
        ImageButton local_check = (ImageButton) findViewById(R.id.local_check);
        findViewById(R.id.check).setOnClickListener(onClickListener);


        //전체동의
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        //필수 서비스이용약관
        CheckBox checkBox2 = (CheckBox)findViewById(R.id.checkbox2);
        //필수 개인정보
        CheckBox checkBox3 =(CheckBox) findViewById(R.id.checkbox3);
        //선택 위치정보
        CheckBox checkBox4 = (CheckBox)findViewById(R.id.checkbox4);


        //전체동의 클릭시
        //전체 true / 전체 false 로 변경
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(true);
                    checkBox4.setChecked(true);
                } else {
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
            }
        });
        //2 클릭시

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //만약 전체 클릭이 true 라면 false로 변경
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                    //각 체크박스 체크 여부 확인해서  전체동의 체크박스 변경
                } else if (checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                    checkBox.setChecked(true);
                }
            }
        });
        //3 클릭시

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                } else if (checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                    checkBox.setChecked(true);
                }
            }
        });

        //4클릭시

        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                } else if (checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                    checkBox.setChecked(true);
                }
            }
        });

        Button btn_agr = (Button) findViewById(R.id.btn_agr1);
        Button btn_agr2 = (Button) findViewById(R.id.btn_agr2);
        Button btn_agr3 = (Button) findViewById(R.id.btn_agr3);
        btn_agr.setText(R.string.underlined_text);
        btn_agr2.setText(R.string.underlined_text);
        btn_agr3.setText(R.string.underlined_text);


        btn_agr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickHandler();
            }
        });

        btn_agr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickHandler();
            }
        });

        btn_agr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickHandler2();
            }
        });

        btn_agr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickHandler3();
            }
        });


        local_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Local_check.class);
                startActivityForResult(intent,1000);
            }
        });




    }

    public void OnClickHandler(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("서비스 이용약관");
        //다이얼로그 창의 내용 입력
        builder.setMessage("약관동의 내용"); //이용약관 @string에 추가해야함
        //다이얼로그창 취소 버튼 추가
        builder.setNegativeButton("닫기",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println(TAG+"이용약관 닫기");
                    }
                });
        //다이얼로그 보여주기
        builder.show();
    }

    public void OnClickHandler2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("개인정보 취급방침");
        //다이얼로그 창의 내용 입력
        builder.setMessage("약관동의 내용"); //이용약관 @string에 추가해야함
        //다이얼로그창 취소 버튼 추가
        builder.setNegativeButton("닫기",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println(TAG+"이용약관 닫기");
                    }
                });
        //다이얼로그 보여주기
        builder.show();
    }

    public void OnClickHandler3(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("위치정보 이용 약관");
        //다이얼로그 창의 내용 입력
        builder.setMessage("약관동의 내용"); //이용약관 @string에 추가해야함
        //다이얼로그창 취소 버튼 추가
        builder.setNegativeButton("닫기",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println(TAG+"이용약관 닫기");
                    }
                });
        //다이얼로그 보여주기
        builder.show();
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


        CheckBox ch1 = (CheckBox) findViewById(R.id.checkbox);
        CheckBox ch2 = (CheckBox) findViewById(R.id.checkbox2);
        CheckBox ch3 = (CheckBox) findViewById(R.id.checkbox3);
        CheckBox ch4 = (CheckBox) findViewById(R.id.checkbox4);





        if(id.length()>0&& password.length()>0&& passwordCheck.length()>0){
            if(password.equals(passwordCheck)) {
                if(address != null) {
                    if (ch2.isChecked() && ch3.isChecked()) {
                        mAuth.createUserWithEmailAndPassword(id, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
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
                                } else {
                                    if (task.getException().toString() != null) {
                                        Toast.makeText(Register.this, "회원가입 실패했습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                }


                            }
                        });

                    } else {
                        Toast.makeText(Register.this, "약관 동의를 해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Register.this, "지역 인증을 해주세요!", Toast.LENGTH_SHORT).show();
                }
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

