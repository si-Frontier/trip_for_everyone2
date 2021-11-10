package com.example.trip_for_everyone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class LoginActivity extends AppCompatActivity /*implements View.OnKeyListener*/{
    private EditText id;
    private EditText password;
    private Button login;
    private Button signUp;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    boolean success;
    public static Activity loginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginActivity = LoginActivity.this;

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        findView();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText, passwordText;
                idText = id.getText().toString();
                passwordText = password.getText().toString();
                if(idText.equals("")||passwordText.equals("")){
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    compare(idText, passwordText);
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }

    //onCreate 끝~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_ENTER){
//            switch(v.getId()){
//                case R.id.id:
//                    break;
//                case R.id.password:
//                    break;
//            }
//            return true;
//        }
//        return false;
//    }

    private void findView(){
        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signUp);
    }

    private boolean compare(String idText, String passwordText) {
        mAuth.signInWithEmailAndPassword(idText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Authentication succes.",
                                    Toast.LENGTH_SHORT).show();
                            success = true;
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
//                            finish();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            id.setText(null);
                            password.setText(null);
                            success = false;
//                            updateUI(null);
                        }
                    }
                });
        return success;
    }


    /*
     public void signUp(View view){
        String email = signinEmail.getText().toString();
        String password = signinPassowrd.getText().toString();
    }
     */



    //이메일 유효성 검사


    //로그인 확인








    //패스워드의 유효성 검사 메소드


        /*
    public static boolean isValidPassword(String password) {
        boolean err = false;
        String regex = "^[a-zA-Z0-9]{8,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        if(m.matches()){
        err = true;
        }

        return err;
    }

    //이메일 유효성 검사 메소드

    public static boolean isValidEmail(String id) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(id);
        if(m.matches()){
            err = true;
        }
        return err;
    }
    */
}