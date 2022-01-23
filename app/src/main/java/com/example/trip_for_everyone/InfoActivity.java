package com.example.trip_for_everyone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class InfoActivity extends AppCompatActivity {

    private boolean isBookmarked;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private InfoFragment1 infoFragment1 = new InfoFragment1();
    private InfoFragment2 infoFragment2 = new InfoFragment2();
    private InfoFragment3 infoFragment3 = new InfoFragment3();

    private DatabaseReference mDatabase;
    private String Address;
    private String uid;
    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.info_fragment,infoFragment1).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.info_navigation);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        TextView spotNameTv = findViewById(R.id.spotName);
        TextView spotAddressTv = findViewById(R.id.spotAddress);
        ImageButton bookmark = findViewById(R.id.info_bookmark);
        picture = findViewById(R.id.pictureViewer);

        //여행지 이름 네비게이션 프래그먼트1 에서 인텐트로 받아오기
        Intent intent = getIntent();
        String spotName = intent.getStringExtra("spotName");
        spotNameTv.setText(spotName);
        FirebaseStorage.getInstance().getReference().child("spot/"+spotName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(picture);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("storageLoad","fail");
            }
        });


        //북마크 판단
        mDatabase.child("users").child(uid).child("bookmark").orderByValue().equalTo(spotName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                isBookmarked = true;
                bookmark.setColorFilter(getResources().getColor(R.color.real_yellow));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isBookmarked = false;
                bookmark.setColorFilter(getResources().getColor(R.color.black));
            }
        });




        // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                mDatabase.child("basicInfo").child(spotName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        BasicInfo group = dataSnapshot.getValue(BasicInfo.class);

                        //각각의 값 받아오기 get어쩌구 함수들은 Together_group_list.class에서 지정한것
                        Address = group.get주소();
                        System.out.println("address"+Address);
                        //'OO구' 까지만 받아오기
                        String[] split = Address.split(" ");
                        System.out.println("split: " + split[1]);
                        //텍스트뷰에 받아온 문자열 대입하기
                        spotAddressTv.setText(split[1]);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                    }
                });


        //InfoFragment1 에 spotName 보내기
        Bundle bundle = new Bundle();
        bundle.putString("spotName", spotName);
        infoFragment1.setArguments(bundle);
        infoFragment3.setArguments(bundle);

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
                                //writeReviewActivity 에 spotName 보내기
                                intent.putExtra("spotName",  spotName);
                                startActivity(intent);

                            }
                        });




        setBookmark();

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBookmarked){
                    bookmark.setColorFilter(getResources().getColor(R.color.black));
                    //  mDatabase.child("place").child("맥도날드")./*장소 받아오기*/child("bookmark").child(uid);
                    mDatabase.child("users").child(uid).child("bookmark").orderByValue().equalTo(spotName).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot child : snapshot.getChildren()){
                               child.getRef().removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    isBookmarked = false;
                }
                else {
                    isBookmarked = true;
                    mDatabase.child("users").child(uid).child("bookmark").push().setValue(spotName);
                    bookmark.setColorFilter(getResources().getColor(R.color.real_yellow));
                }
            }
        });
    }

    public void setBookmark() {

        /*
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("place").child("맥도날드").child("bookmark").child("uid").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase::", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase:", String.valueOf(task.getResult().getValue()));
                }
            }
        });

*/
    }



}
