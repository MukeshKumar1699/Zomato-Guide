package com.mukeshproject.zomatoguide.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.listofreviews.UserReviewsItem;

import static android.view.View.GONE;

public class ListOfReviews_Rc_ViewHolder extends RecyclerView.ViewHolder {

    private ImageView starImage1, starImage2, starImage3, starImage4, starImage5, emptystarImage1, emptystarImage2, emptystarImage3, emptystarImage4;
    private TextView tv_profilePicText, tv_userName, tv_userRating, tv_userComment, tv_timeFrendly, tv_like, tv_comment;

    public ListOfReviews_Rc_ViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View view) {

        starImage1 = view.findViewById(R.id.starImage1);
        starImage2 = view.findViewById(R.id.starImage2);
        starImage3 = view.findViewById(R.id.starImage3);
        starImage4 = view.findViewById(R.id.starImage4);
        starImage5 = view.findViewById(R.id.starImage5);

        emptystarImage1 = view.findViewById(R.id.emptyStarImage1);
        emptystarImage2 = view.findViewById(R.id.emptyStarImage2);
        emptystarImage3 = view.findViewById(R.id.emptyStarImage3);
        emptystarImage4 = view.findViewById(R.id.emptyStarImage4);

        tv_profilePicText = view.findViewById(R.id.tv_profilePicText);
        tv_userName = view.findViewById(R.id.tv_userName);
        tv_userRating = view.findViewById(R.id.tv_userRating);
        tv_userComment = view.findViewById(R.id.tv_userComment);
        tv_timeFrendly = view.findViewById(R.id.tv_timeFrendly);
        tv_like = view.findViewById(R.id.tv_like);
        tv_comment = view.findViewById(R.id.tv_comment);
    }

    public void setData(UserReviewsItem userReviewsItem) {

        //tv_profilePicText.setText(userReviewsItem.getReview().getUser().getName().toUpperCase().charAt(0));
        //tv_profilePicText.setBackgroundColor(Color.parseColor("#"+userReviewsItem.getReview().getUser().getFoodieColor()));

        tv_userName.setText(userReviewsItem.getReview().getUser().getName() + " Wrote a " + userReviewsItem.getReview().getRating() + " star review");
        tv_userRating.setText("" + userReviewsItem.getReview().getRating() + ".0");

        if (userReviewsItem.getReview().getRating() == 5) {
            emptystarImage1.setVisibility(GONE);
            emptystarImage2.setVisibility(GONE);
            emptystarImage3.setVisibility(GONE);
            emptystarImage4.setVisibility(GONE);
        } else if (userReviewsItem.getReview().getRating() == 4) {
            emptystarImage2.setVisibility(GONE);
            emptystarImage3.setVisibility(GONE);
            emptystarImage4.setVisibility(GONE);
            starImage5.setVisibility(GONE);
        } else if (userReviewsItem.getReview().getRating() == 3) {
            emptystarImage3.setVisibility(GONE);
            emptystarImage4.setVisibility(GONE);
            starImage4.setVisibility(GONE);
            starImage5.setVisibility(GONE);
        } else if (userReviewsItem.getReview().getRating() == 2) {
            emptystarImage2.setVisibility(GONE);
            starImage3.setVisibility(GONE);
            starImage4.setVisibility(GONE);
            starImage5.setVisibility(GONE);
        } else if (userReviewsItem.getReview().getRating() == 1) {
            starImage5.setVisibility(GONE);
            starImage4.setVisibility(GONE);
            starImage3.setVisibility(GONE);
            starImage2.setVisibility(GONE);

        }
        //tv_userRating.setTextColor(Color.parseColor(userReviewsItem.getReview().getRatingColor().toLowerCase()));
        tv_userComment.setText(userReviewsItem.getReview().getRatingText());
        tv_timeFrendly.setText(userReviewsItem.getReview().getReviewTimeFriendly());

//        tv_like.setText(userReviewsItem.getReview().getLikes());
//        tv_comment.setText(", " +userReviewsItem.getReview().getCommentsCount());
    }

}
