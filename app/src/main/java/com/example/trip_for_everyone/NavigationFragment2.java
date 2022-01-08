package com.example.trip_for_everyone;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;




/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigationFragment2#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class NavigationFragment2 extends Fragment {
    TextView mypage_etc;
    TextView mypage_service_guide;
    TextView mypage_account;
    TextView myapge_app_setting;
    ImageView member_image;
    TextView member_res_text;
    TextView member_name_text;
    Button profile_edit_button;
    Button mypage_logout_button;
    Button course_text;
    Button alarm;
    Button myreview_text;
    Button bookmark_text;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String name;
    private DatabaseReference mDatabase;

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
        mypage_etc = (TextView)view.findViewById(R.id.mypage_etc);
        mypage_service_guide = (TextView)view.findViewById(R.id.mypage_service_guide);
        mypage_account = (TextView)view.findViewById(R.id.mypage_account);
        myapge_app_setting = (TextView)view.findViewById(R.id.myapge_app_setting);
        profile_edit_button = (Button)view.findViewById(R.id.profile_edit_button);
        member_image = (ImageView)view.findViewById(R.id.member_image);
        member_res_text = (TextView)view.findViewById(R.id.member_res_text);
        member_name_text = (TextView)view.findViewById(R.id.member_name_text);
        profile_edit_button = (Button) view.findViewById(R.id.profile_edit_button);
        mypage_logout_button = (Button) view.findViewById(R.id.mypage_logout_button);
        course_text = (Button) view.findViewById(R.id.course_text);
        alarm = (Button) view.findViewById(R.id.alarm);
        myreview_text = (Button) view.findViewById(R.id.myreview_text);
        bookmark_text = (Button) view.findViewById(R.id.bookmark_text);




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
        mDatabase = FirebaseDatabase.getInstance().getReference();
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



        mypage_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(view.getContext(), Mypage_main_logout.class);
                startActivity(intent);
            }
        });
        return view;
    }
}


