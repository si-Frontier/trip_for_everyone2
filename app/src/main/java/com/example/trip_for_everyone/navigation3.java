package com.example.trip_for_everyone;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link navigation3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class navigation3 extends Fragment {

    TextView schedule_text;
    TextView alarm_text;
    ImageView mypage_line;
    TextView bookmark_text;
    TextView course_text;
    Button mypage_login_button;
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
    //private FragmentManager fragmentManager = getSupportFragmentManager();
    private NavigationFragment navigationFragment = new NavigationFragment();
    private NavigationFragment1 navigationFragment1 = new NavigationFragment1();
    private NavigationFragment2 navigationFragment2 = new NavigationFragment2();



    public navigation3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment navigation3.
     */
    // TODO: Rename and change types and number of parameters
    public static navigation3 newInstance(String param1, String param2) {
        navigation3 fragment = new navigation3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        //return inflater.inflate(R.layout.fragment_navigation3, container, false);
        View view = inflater.inflate(R.layout.fragment_navigation3, container, false);
        schedule_text = (TextView)view.findViewById(R.id.schedule_text);
        alarm_text = (TextView)view.findViewById(R.id.alarm_text);
        address_text_view = (TextView)view.findViewById(R.id.addressTextView);
        mypage_line = (ImageView)view.findViewById(R.id.mypage_line);
        bookmark_text = (TextView)view.findViewById(R.id.bookmark_text);
        course_text = (TextView)view.findViewById(R.id.course_text);
        mypage_login_button = (Button)view.findViewById(R.id.mypage_login_button);
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



        // addValueEventListener
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();



        FirebaseStorage storage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference fileUrl = mDatabase.child(uid);
        StorageReference storageRef = storage.getReference(); //스토리지참고


        mypage_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
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

        bookmark_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getContext(), BookmarkPage.class);
                startActivity(intent);
            }
        });

        return view;
    }
}