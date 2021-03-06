package com.mukeshproject.zomatoguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshproject.zomatoguide.listeners.ItemClickListenerSearchRestaurant;
import com.mukeshproject.zomatoguide.listofrestaurants.RestaurantsItem;
import com.mukeshproject.zomatoguide.viewholder.ListOfRestaurants_Rc_ViewHolder;

import java.util.List;

public class ListOfGoOutRestaurants_Rc_Adapter extends RecyclerView.Adapter<ListOfRestaurants_Rc_ViewHolder> {
    private final List<RestaurantsItem> restaurantsItemList;
    private final ItemClickListenerSearchRestaurant itemClickListenerSearchRestaurant;

    public ListOfGoOutRestaurants_Rc_Adapter(List<RestaurantsItem> restaurantsItems, ItemClickListenerSearchRestaurant itemClickListenerSearchRestaurant) {
        this.restaurantsItemList = restaurantsItems;
        this.itemClickListenerSearchRestaurant = itemClickListenerSearchRestaurant;
    }

    @NonNull
    @Override
    public ListOfRestaurants_Rc_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_listofrestaurant_design, parent, false);
        return new ListOfRestaurants_Rc_ViewHolder(view, itemClickListenerSearchRestaurant);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOfRestaurants_Rc_ViewHolder holder, int position) {

        RestaurantsItem restaurantsItems = restaurantsItemList.get(position);
        holder.setData(restaurantsItems);
    }

    @Override
    public int getItemCount() {
        return restaurantsItemList.size();
    }
}
