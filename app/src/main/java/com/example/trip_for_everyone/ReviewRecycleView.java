package com.example.trip_for_everyone;

import android.content.Intent;
import android.graphics.Outline;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class ReviewRecycleView  extends RecyclerView.Adapter<ReviewRecycleView.ItemViewHolder> {

    private ArrayList<Review> listData = new ArrayList<>();

    @NonNull
    @Override
    public ReviewRecycleView.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    public void setReviewmarkList(ArrayList<Review> list){
        this.listData = list;
    }


    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Review data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView content_Tv;
        private TextView review_count_Tv;
        private TextView name_Tv;
        private RatingBar ratingBar;
        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);
            content_Tv = itemView.findViewById(R.id.item_content);
            name_Tv = itemView.findViewById(R.id.item_name);
            imageView = itemView.findViewById(R.id.item_profile_img);
            ratingBar = itemView.findViewById(R.id.item_star);
       }

        void onBind(Review data){
            name_Tv.setText(data.getcontent());
            content_Tv.setText(data.getcontent());
            name_Tv.setText(data.getname());
            ratingBar.setRating(data.getstar());
            //리뷰 카운트 만들기

        }
    }
}
