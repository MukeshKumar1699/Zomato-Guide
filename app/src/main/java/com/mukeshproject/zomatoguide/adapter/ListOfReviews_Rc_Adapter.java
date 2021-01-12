package com.mukeshproject.zomatoguide.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.listofreviews.UserReviewsItem;
import com.mukeshproject.zomatoguide.viewholder.ListOfReviews_Rc_ViewHolder;

import java.util.List;


public class ListOfReviews_Rc_Adapter extends RecyclerView.Adapter<ListOfReviews_Rc_ViewHolder> {

    private final List<UserReviewsItem> userReviewsItemList;

    public ListOfReviews_Rc_Adapter(List<UserReviewsItem> userReviewsItemList) {
        this.userReviewsItemList = userReviewsItemList;
    }

    @NonNull
    @Override
    public ListOfReviews_Rc_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_listofuserreview_design, parent, false);
        return new ListOfReviews_Rc_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOfReviews_Rc_ViewHolder holder, int position) {

        UserReviewsItem userReviewsItem = userReviewsItemList.get(position);
        holder.setData(userReviewsItem);
    }

    @Override
    public int getItemCount() {
        return userReviewsItemList.size();
    }
}
