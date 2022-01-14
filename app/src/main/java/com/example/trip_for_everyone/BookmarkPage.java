package com.example.trip_for_everyone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class BookmarkPage extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String uid;
    private RecyclerView mRecyclerView;
    private BookmarkRecyclerView mAdapter;
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_page);
        list = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        mRecyclerView = (RecyclerView) findViewById(R.id.bookmark_recycler_view);
        mAdapter = new BookmarkRecyclerView();

        mDatabase.child("users").child(uid).child("bookmark").orderByChild("bookmark").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    list.add(child.getValue().toString());
                }
                mAdapter.setBookmarkList(list);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



    }
}