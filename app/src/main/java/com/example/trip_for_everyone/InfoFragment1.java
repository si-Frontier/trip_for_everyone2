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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DatabaseReference mDatabase;

    private String spotAddress;
    private String time;
    private String closedDay;
    private String admission;
    private String parking;
    private Double lat,lon;
    private String familyToilet;
    private String toilet;
    private String rental;
    private String amenities;
    private String DisabledToilet;
    private String DisabledParking;

    public InfoFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment1 newInstance(String param1, String param2) {
        InfoFragment1 fragment = new InfoFragment1();
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
        View view = inflater.inflate(R.layout.fragment_info1, container, false);

        // Inflate the layout for this fragment
        TextView spotAddressTv = view.findViewById(R.id.info_address_textView);
        TextView timeTv = view.findViewById(R.id.info_time_textView);
        TextView closedDayTv = view.findViewById(R.id.info_closed_day_textView);
        TextView admissionTv = view.findViewById(R.id.info_admission_textView);
        TextView parkingTv = view.findViewById(R.id. info_parking_textView);

        TextView toiletTv = view.findViewById(R.id.info_toilet_textView);
       // TextView amenitiesTv = view.findViewById(R.id. info_amenities_textView);
     //   TextView DisabledToiletTv = view.findViewById(R.id. info_Disabled_toilet_textView);
     //   TextView DisabledParkingTv = view.findViewById(R.id. info_Disabled_parking_textView);
     //   TextView familyToiletTv = view.findViewById(R.id.info_family_toilet);

        ImageButton mapButton = view.findViewById(R.id.info_map_button);


        //?????? ??????. getArguments() ???????????? ??????.
        Bundle bundle = getArguments();
        if(bundle != null) {
            String spotName = bundle.getString("spotName"); //Name ??????.
            System.out.println("info / spotname : :::" + spotName);


            //????????? ????????? ??????
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("basicInfo").child(spotName).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    BasicInfo group = dataSnapshot.getValue(BasicInfo.class);
                   //?????? ??????
                    spotAddress = group.get??????();
                    spotAddressTv.setText(spotAddress);
                    //????????????
                    time= group.get????????????();
                    timeTv.setText(time);
                    //?????????
                    closedDay= group.get?????????();
                    closedDayTv.setText(closedDay);
                    System.out.println(closedDay);
                    //????????????
                    admission=group.get????????????();
                    if(admission.equals("????????????"))
                        admission="??????";
                    admissionTv.setText(admission);
                    System.out.println(admission);

                    //

                   // Info infoGroup = dataSnapshot.getValue(Info.class);
                   // familyToilet= infoGroup.get???????????????();
                   // familyToiletTv.setText(familyToilet);

                    lat = group.getLatitude();
                    lon = group.getLongitude();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //Log.e("MainActivity", String.valueOf(databaseError.toException())); // ????????? ??????
                }
            });
        }
/////////////////////////???????????? ???????????? ???????????? ???????????????
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lat==null || lon==null) Log.e("null exception","cant find longitude or latitude");
                else {
                    String url = "kakaomap://look?p=" + lat.toString() + "," + lon.toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            }
        });

        return view;


    }

}
