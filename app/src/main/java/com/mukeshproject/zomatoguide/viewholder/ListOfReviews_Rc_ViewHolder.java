package com.mukeshproject.zomatoguide.viewholder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.listofreviews.UserReviewsItem;

public class ListOfReviews_Rc_ViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_profilePicText, tv_userName, tv_userComment, tv_timeFrendly, tv_like, tv_comment;
    private RatingBar ratingBar;

    public ListOfReviews_Rc_ViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View view) {

        ratingBar = view.findViewById(R.id.ratingBar);
        tv_profilePicText = view.findViewById(R.id.tv_profilePicText);
        tv_userName = view.findViewById(R.id.tv_userName);
        tv_userComment = view.findViewById(R.id.tv_userComment);
        tv_timeFrendly = view.findViewById(R.id.tv_timeFrendly);
        tv_like = view.findViewById(R.id.tv_like);
        tv_comment = view.findViewById(R.id.tv_comment);
    }

    @SuppressLint("SetTextI18n")
    public void setData(UserReviewsItem userReviewsItem) {

//        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
//        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        tv_profilePicText.setText(""+userReviewsItem.getReview().getUser().getName().toUpperCase().charAt(0));
//        tv_profilePicText.setBackgroundColor(Color.parseColor("#"+userReviewsItem.getReview().getUser().getFoodieColor()));

        tv_userName.setText(userReviewsItem.getReview().getUser().getName() + " Wrote a " + userReviewsItem.getReview().getRating() + " star review");

        ratingBar.setRating(userReviewsItem.getReview().getRating());

        //tv_userRating.setTextColor(Color.parseColor(userReviewsItem.getReview().getRatingColor().toLowerCase()));
        tv_userComment.setText(userReviewsItem.getReview().getRatingText());
        tv_timeFrendly.setText(userReviewsItem.getReview().getReviewTimeFriendly());

        tv_like.setText(""+userReviewsItem.getReview().getLikes());
        tv_comment.setText(""+userReviewsItem.getReview().getCommentsCount());
    }

}
