package com.example.trip_for_everyone;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;


import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.daum.mf.map.api.MapView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private LoginActivity loginActivity;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private NavigationFragment navigationFragment = new NavigationFragment();
    private NavigationFragment1 navigationFragment1 = new NavigationFragment1();
    private NavigationFragment2 navigationFragment2 = new NavigationFragment2();

    private String name;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getHashKey();
//        if(true/*첫 로그인일 경우*/){
//            Intent intent = new Intent(getApplicationContext(), CardNewsActivity.class);
//            startActivity(intent);
//        }


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.navigationFragments, navigationFragment).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationBar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.menu1:
                        fragmentTransaction.replace(R.id.navigationFragments, navigationFragment).commitAllowingStateLoss();
                        break;
                    case R.id.menu2:
                        fragmentTransaction.replace(R.id.navigationFragments, navigationFragment1).commitAllowingStateLoss();
                        break;
                    case R.id.menu3:
                        fragmentTransaction.replace(R.id.navigationFragments, navigationFragment2).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
        mAuth = FirebaseAuth.getInstance();
        loginActivity = (LoginActivity) LoginActivity.loginActivity;
        loginActivity.finish();

//        if(user == null){
//            Intent loginIntent = new Intent(this, LoginActivity.class); //로그인 인텐트 만들어서 넘기고 LoginAcitivy if문 수정
//            startActivity(loginIntent);
//        }



        // 사용자 이름가져오기 ( 딜레이 문제 )
        /*
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            System.out.println(uid);
            mDatabase.child("users").child(uid).child("userName").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                       // name  = String.valueOf(task.getResult().getValue());
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    }
                }

            });

            */



            // 데이터베이스 읽기 #2. Single ValueEventListener
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("users").child(uid).child("userName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        name = dataSnapshot.getValue(String.class);
                        System.out.println("fname"+ name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                    }
                });





            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            System.out.println(name);
            navigationFragment2.setArguments(bundle);
        }
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }


    }


