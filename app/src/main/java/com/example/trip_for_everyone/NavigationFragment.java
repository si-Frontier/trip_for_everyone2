package com.example.trip_for_everyone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator3;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationFragment extends Fragment {

    ViewPager2 viewPager2;
    StorageDownload download;
    CircleIndicator3 indicator;
    MainActivity activity;
    ImageView top_one_img;
    Button tmpInfoBtn;


    private InfoFragment1 infoFragment1 = new InfoFragment1();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        activity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach(){
        super.onDetach();

        activity = null;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavigationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationFragment newInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
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
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        final int pageLimit = 5;
        viewPager2 = (ViewPager2)view.findViewById(R.id.viewPager);
        /////////////////tmp///////////////

        //여행지 클릭시 여행지 이름 인텐트에 담기

        Button tmpButton = view.findViewById(R.id.tmpInfoBtn);

        tmpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tmpintent = new Intent(getContext(), InfoActivity.class);
                tmpintent.putExtra("spotName",  tmpButton.getText());
                startActivity(tmpintent);


            }
        });
        GregorianCalendar today = new GregorianCalendar();
        Integer year = today.get(today.YEAR);
        Integer month = today.get(today.MONTH);
        int day = today.get(today.DATE);
        Random rand = new Random(Integer.parseInt(year.toString()+month.toString()+day));
        ////////////////////지울거////////////////
        ArrayList<String> path = new ArrayList<>();     //추천할 여행지를여기에 추가하면 됨
//        path.add("spot/블록시티");
//        path.add("test/palace.png");
//        path.add("test/ic_logo.png");
        DatabaseReference mdataBase = FirebaseDatabase.getInstance().getReference();
        mdataBase.child("basicInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int tmp = 0;
                int random = rand.nextInt((int)snapshot.getChildrenCount()-3);
                Log.d("gyu",random+" ");

                for(DataSnapshot data : snapshot.getChildren()){
                    if(tmp>=random&&tmp<random+3){
                        Log.d("gyu",data.getKey());
                        path.add("spot/"+data.getKey());

                    }
                    tmp++;
                }
                viewPager2.setAdapter(new ViewPagerAdapter(path));
                indicator = view.findViewById(R.id.indicator);
                indicator.setViewPager(viewPager2);
                indicator.createIndicators(path.size(),0);
                /////////////////여기
                viewPager2.setOffscreenPageLimit(pageLimit);
                viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                        if (positionOffsetPixels == 0) {
                            viewPager2.setCurrentItem(position);
                        }
                    }

                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        indicator.animatePageSelected(position);
                    }

                });

                viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
                    @Override
                    public void transformPage(@NonNull View page, float position) {
                        float myOffset = position * -(80);
                        if (viewPager2.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                            if (ViewCompat.getLayoutDirection(viewPager2) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                                page.setTranslationX(-myOffset);
                            } else {
                                page.setTranslationX(myOffset);
                            }
                        } else {
                            page.setTranslationY(myOffset);
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //////////////////여기




        return view;
    }
}