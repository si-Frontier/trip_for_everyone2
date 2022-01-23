package com.example.trip_for_everyone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private WriteReviewActivity writeReviewActivity = new WriteReviewActivity();
    private DatabaseReference mDatabase;
    private String uid;
    private RecyclerView mRecyclerView;
    private ReviewRecycleView mAdapter;
    private ArrayList<Review> list;

    private Context mcontext;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment3 newInstance(String param1, String param2) {
        InfoFragment3 fragment = new InfoFragment3();
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
    public void onAttach(Context context){
        super.onAttach(context);
        mcontext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info3, container, false);

        list = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        mRecyclerView = (RecyclerView)view.findViewById(R.id.review_recycler_view);
        mAdapter = new ReviewRecycleView();

        //번들 받기. getArguments() 메소드로 받음.
      //  Bundle bundle = getArguments();
        //if(bundle != null) {
           // String spotName = bundle.getString("spotName"); //Name 받기.
            //System.out.println("info / spotname : :::" + spotName);
            String spotName="블록시티";
            mDatabase.child("review").child(spotName).child(uid).orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                    for (DataSnapshot child : snapshot.getChildren()) {
                        //Review review = snapshot.getValue(Review.class);
                       // review.getname();
                      //  list.add(child.getValue(Review.class));
                       System.out.println("child"+ snapshot.getValue(Review.class));
                    }
                    mAdapter.setReviewmarkList(list);
                  //  mRecyclerView.setAdapter(mAdapter);
                  //  mRecyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
                }



                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });


    //    }

        // Inflate the layout for this fragment
        return view;
    }

    }