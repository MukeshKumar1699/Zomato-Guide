package com.mukeshproject.zomatoguide.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.listeners.ItemClickListenerSearchRestaurant;
import com.mukeshproject.zomatoguide.listofrestaurants.RestaurantsItem;

public class ListOfRestaurants_Rc_ViewHolder extends RecyclerView.ViewHolder {

    private ItemClickListenerSearchRestaurant itemClickListenerSearchRestaurant;
    private RelativeLayout rl_restaurantItem;
    private ImageView iv_restaurantImage;
    private TextView tv_restaurantRating, tv_restaurantName, tv_restaurantCusines, tv_restaurantCostfor2, tv_restaurantTiming;

    public ListOfRestaurants_Rc_ViewHolder(@NonNull View itemView, ItemClickListenerSearchRestaurant itemClickListenerSearchRestaurant) {
        super(itemView);
        initViews(itemView, itemClickListenerSearchRestaurant);
    }

    private void initViews(View view, ItemClickListenerSearchRestaurant itemClickListenerSearchRestaurant) {

        this.itemClickListenerSearchRestaurant = itemClickListenerSearchRestaurant;
        rl_restaurantItem = view.findViewById(R.id.rl_restaurantItem);
        iv_restaurantImage = view.findViewById(R.id.iv_restaurantImageRclist);
        tv_restaurantRating = view.findViewById(R.id.tv_restaurantRatingRcList);
        tv_restaurantName = view.findViewById(R.id.tv_restaurantNameRclist);
        tv_restaurantCusines = view.findViewById(R.id.tv_restaurantCuisinesRclist);
        tv_restaurantCostfor2 = view.findViewById(R.id.tv_restaurantCostfor2Rclist);
        tv_restaurantTiming = view.findViewById(R.id.tv_restaurantTimingRclist);
    }

    public void setData(RestaurantsItem restaurantsItemList) {

        Glide.with(iv_restaurantImage.getContext()).
                load(restaurantsItemList.getRestaurant().getFeaturedImage()).
                into(iv_restaurantImage);

        tv_restaurantRating.setText(restaurantsItemList.getRestaurant().getUserRating().getAggregateRating() + "/5");
        tv_restaurantName.setText(restaurantsItemList.getRestaurant().getName());
        tv_restaurantCusines.setText(restaurantsItemList.getRestaurant().getCuisines());
        tv_restaurantCostfor2.setText(restaurantsItemList.getRestaurant().getAverageCostForTwo() + " for two");
        tv_restaurantTiming.setText("Opens at " + restaurantsItemList.getRestaurant().getTimings());

        rl_restaurantItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListenerSearchRestaurant.onItemClick(getAdapterPosition(), restaurantsItemList.getRestaurant());
            }
        });
    }
}
