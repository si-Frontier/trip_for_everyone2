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


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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