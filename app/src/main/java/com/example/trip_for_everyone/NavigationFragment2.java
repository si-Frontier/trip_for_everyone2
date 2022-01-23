package com.example.trip_for_everyone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigationFragment2#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class NavigationFragment2 extends Fragment {
    TextView schedule_text;
    TextView alarm_text;
    ImageView mypage_line;
    TextView bookmark_text;
    TextView course_text;
    Button mypage_logout_button;
    ImageButton course_button;
    de.hdodenhof.circleimageview.CircleImageView mypage_image;
    ImageView residence_image;
    ImageButton profile_edit_button;
    TextView member_name_text;
    ImageButton bookmark_button;
    ImageButton schedule_button;
    ImageButton alarm_button;
    TextView mypage_etc_text;
    TextView mypage_account_text;
    TextView mypage_setting_text;
    TextView mypage_announcement_text;
    Button mypage_account_button;
    Button mypage_setting_button;
    Button mypage_announcement_button;
    Button mypage_etc_button;
    ImageView alarm_point;
    TextView address_text_view;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String name;
    private String address;
    private DatabaseReference mDatabase;
    private Context mContext;

    MainActivity activity;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavigationFragement3.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationFragment2 newInstance(String param1, String param2) {
        NavigationFragment2 fragment = new NavigationFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        mContext = context;
        activity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach(){
        super.onDetach();

        activity = null;
    }
    public NavigationFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation2, container, false);
        schedule_text = (TextView)view.findViewById(R.id.schedule_text);
        alarm_text = (TextView)view.findViewById(R.id.alarm_text);
        address_text_view = (TextView)view.findViewById(R.id.addressTextView);
        mypage_line = (ImageView)view.findViewById(R.id.mypage_line);
        bookmark_text = (TextView)view.findViewById(R.id.bookmark_text);
        course_text = (TextView)view.findViewById(R.id.course_text);
        mypage_logout_button = (Button)view.findViewById(R.id.mypage_logout_button);
        course_button = (ImageButton)view.findViewById(R.id.course_button);
        mypage_image = (de.hdodenhof.circleimageview.CircleImageView)view.findViewById(R.id.mypage_image);
        residence_image = (ImageView)view.findViewById(R.id.residence_image);
        profile_edit_button = (ImageButton)view.findViewById(R.id.profile_edit_button);
        member_name_text = (TextView)view.findViewById(R.id.member_name_text);
        bookmark_button = (ImageButton)view.findViewById(R.id.bookmark_button);
        schedule_button = (ImageButton)view.findViewById(R.id.schedule_button);
        alarm_button = (ImageButton)view.findViewById(R.id.alarm_button);
        mypage_etc_text = (TextView)view.findViewById(R.id.mypage_etc_text);
        mypage_account_text = (TextView)view.findViewById(R.id.mypage_account_text);
        mypage_setting_text = (TextView)view.findViewById(R.id.mypage_setting_text);
        mypage_announcement_text = (TextView)view.findViewById(R.id.mypage_announcement_text);
        mypage_account_button = (Button)view.findViewById(R.id.mypage_account_button);
        mypage_setting_button = (Button)view.findViewById(R.id.mypage_setting_button);
        mypage_announcement_button = (Button)view.findViewById(R.id.mypage_announcement_button);
        mypage_etc_button = (Button)view.findViewById(R.id.mypage_etc_button);
        alarm_point = (ImageView)view.findViewById(R.id.alarm_point);



        //FragmentManager fragmentManager = getSupportFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.navigationFragments, navigationFragment).commitAllowingStateLoss();
        // BottomNavigationView bottomNavigationView = findViewById(R.id.navigationBar);
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                switch (item.getItemId()) {
//                    case R.id.menu1:
//                        fragmentTransaction.replace(R.id.navigationFragments, navigationFragment).commitAllowingStateLoss();
//                        break;
//                    case R.id.menu2:
//                        fragmentTransaction.replace(R.id.navigationFragments, navigationFragment1).commitAllowingStateLoss();
//                        break;
//                    case R.id.menu3:
//                        fragmentTransaction.replace(R.id.navigationFragments, navigationFragment2).commitAllowingStateLoss();
//                        break;
//                }
//                return true;
//            }
//        });

/*
        //현재 로그인한 사용자 이름
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            mDatabase.child("users").child(uid).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("MainActivity", "Single ValueEventListener : " + snapshot.getValue());
                    name  = (String) snapshot.getValue();
                    member_name_text.setText(name+"님");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
*/


        /*
        // 사용자 이름가져오기 ( 딜레이 문제 )
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            mDatabase.child("users").child(uid).child("userName").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        name  = String.valueOf(task.getResult().getValue());
                        member_name_text.setText(name+"님");
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    }
                }
            });
        }
*/






        // addValueEventListener
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();



        FirebaseStorage storage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference fileUrl = mDatabase.child(uid);
        StorageReference storageRef = storage.getReference(); //스토리지참고
        storageRef.child("images/"+"users/"+fileUrl).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시

                Log.d("file",String.valueOf(uri));
                Glide.with(mContext
                ).load(uri).into(mypage_image);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
                Toast.makeText(getContext(), "프로필 사진을 등록해주세요", Toast.LENGTH_SHORT).show();
            }
        });



        mDatabase.child("users").child(uid).child("userName").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(String.class);
                System.out.println("fname"+ name);
                member_name_text.setText(name+"님");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });


        //  String name = getArguments().getString("name");

        mDatabase.child("users").child(uid).child("address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                address = snapshot.getValue(String.class);
                System.out.println("faddress"+ address);
                address_text_view.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        mypage_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //여기 실행이 이상함, 프래그먼트 불러오는거 더 찾아보기
//                Intent intent = new Intent(getActivity(), navigation3.class);
//                startActivity(intent);
                activity.onFragmentChange(3);
                //Intent intent = new Intent(view.getContext(),Mypage_main.class);
                //startActivity(intent);
            }
        });

        mypage_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), mypage_account.class);
                startActivity(intent);
            }
        });

        mypage_setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), mypage_setting.class);
                startActivity(intent);
            }
        });

        mypage_announcement_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), mypage_announcement.class);
                startActivity(intent);
            }
        });

        mypage_etc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), mypage_etc.class);
                startActivity(intent);
            }
        });

        profile_edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), profile_edit.class);
                startActivity(intent);
            }
        });

        alarm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), mypage_alarm.class);
                startActivity(intent);
            }
        });

        bookmark_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getContext(), BookmarkPage.class);
                startActivity(intent);
            }
        });

        schedule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getContext(), Mypage_schedule.class);
                startActivity(intent);
            }
        });

        course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), mypage_course.class);
                startActivity(intent);
            }
        });

//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference fileUrl = mDatabase.child(uid);
//        storageRef.child("images/"+"users/"+fileUrl).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                //이미지 로드 성공시
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                //이미지 로드 실패시
//                Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }



//    private void getFireBaseProfileImage(int num){
//        File file = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES+"/profile_img");
//        if(!file.isDirectory()){
//            file.mkdir();
//        }
//        downloadImage(num);
//    }
//
//    private void downloadImage(int num){
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        String uid = user.getUid();
//        DatabaseReference fileUrl = mDatabase.child(uid);
//        StorageReference storageRef = storage.getReference(); //스토리지참고
//        storageRef.child("images/"+"users/"+fileUrl).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                //이미지 로드 성공시
//
//                Log.d("file",String.valueOf(uri));
//                Glide.with(getContext()).load(uri).into(mypage_image);
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                //이미지 로드 실패시
//                Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}


