package com.example.trip_for_everyone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class InfoActivity extends AppCompatActivity {
    private ImageButton bookmark;
    private boolean isBookmarked;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private InfoFragment1 infoFragment1 = new InfoFragment1();
    private InfoFragment2 infoFragment2 = new InfoFragment2();
    private InfoFragment3 infoFragment3 = new InfoFragment3();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        isBookmarked = false;

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.info_fragment,infoFragment1).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.info_navigation);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();   //여기서 다시 선언해서 써야됨
                switch(item.getItemId()){
                    case R.id.infomenu1:
                        fragmentTransaction.replace(R.id.info_fragment,infoFragment1).commitAllowingStateLoss();
                        fab.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.infomenu2:
                        fragmentTransaction.replace(R.id.info_fragment,infoFragment2).commitAllowingStateLoss();
                        fab.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.infomenu3:
                        fragmentTransaction.replace(R.id.info_fragment, infoFragment3).commitAllowingStateLoss();
                        fab.setVisibility(View.VISIBLE);


                        break;
                }
                return false;
            }

        });

                      fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //    Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                                Intent intent = new Intent(getApplicationContext(), WriteReviewActivity.class);
                               startActivity(intent);

                            }
                        });

        bookmark = findViewById(R.id.info_bookmark);
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBookmarked){
                    bookmark.setColorFilter(getResources().getColor(R.color.black));
                    isBookmarked = false;
                }
                else {
                    isBookmarked = true;
                    bookmark.setColorFilter(getResources().getColor(R.color.real_yellow));
                }
            }
        });
    }
}
