package com.example.trip_for_everyone;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStateAdapter {
    //    ArrayList<Fragment> items = new ArrayList<>();
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0) return new Page1();
        else if(position == 1) return new Page2();
        else return new Page3();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}