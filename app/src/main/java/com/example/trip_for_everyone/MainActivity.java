package com.example.trip_for_everyone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private LoginActivity loginActivity;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private NavigationFragment1 navigationFragment1 = new NavigationFragment1();
    private NavigationFragment2 navigationFragment2 = new NavigationFragment2();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(true/*첫 로그인일 경우*/){
            Intent intent = new Intent(getApplicationContext(), CardNewsActivity.class);
            startActivity(intent);
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.navigationFragments, navigationFragment1).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationBar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.menu1:
                        fragmentTransaction.replace(R.id.navigationFragments, navigationFragment1).commitAllowingStateLoss();
                        break;
                    case R.id.menu2:
                        fragmentTransaction.replace(R.id.navigationFragments, navigationFragment2).commitAllowingStateLoss();
                        break;
                    case R.id.menu3:
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
    }
}