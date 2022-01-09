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
import android.widget.ImageButton;
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

import org.w3c.dom.Text;


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
    ImageView mypage_image;
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
        schedule_text = (TextView)view.findViewById(R.id.schedule_text);
        alarm_text = (TextView)view.findViewById(R.id.alarm_text);
        address_text_view = (TextView)view.findViewById(R.id.addressTextView);
        mypage_line = (ImageView)view.findViewById(R.id.mypage_line);
        bookmark_text = (TextView)view.findViewById(R.id.bookmark_text);
        course_text = (TextView)view.findViewById(R.id.course_text);
        mypage_logout_button = (Button)view.findViewById(R.id.mypage_logout_button);
        course_button = (ImageButton)view.findViewById(R.id.course_button);
        mypage_image = (ImageView)view.findViewById(R.id.mypage_image);
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
                Intent intent = new Intent(view.getContext(), Mypage_main_logout.class);
                startActivity(intent);
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

        return view;
    }
}


