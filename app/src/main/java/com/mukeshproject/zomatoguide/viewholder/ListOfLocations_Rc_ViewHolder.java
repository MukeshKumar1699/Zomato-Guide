package com.mukeshproject.zomatoguide.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.listeners.ItemClickListenerSearchLocation;
import com.mukeshproject.zomatoguide.listoflocations.LocationSuggestionsItem;

public class ListOfLocations_Rc_ViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_cityName, tv_countryName;
    private ImageView iv_countryFlag;
    private RelativeLayout rl_rcLocation;
    private ItemClickListenerSearchLocation itemClickListenerSearchLocation;

    public ListOfLocations_Rc_ViewHolder(@NonNull View itemView, ItemClickListenerSearchLocation itemClickListenerSearchLocation) {
        super(itemView);
        initViews(itemView, itemClickListenerSearchLocation);
    }

    private void initViews(View view, ItemClickListenerSearchLocation itemClickListenerSearchLocation) {

        rl_rcLocation = view.findViewById(R.id.rl_rcLocation);
        tv_cityName = view.findViewById(R.id.tv_cityName);
        tv_countryName = view.findViewById(R.id.tv_countryName);
        iv_countryFlag = view.findViewById(R.id.iv_countryFlag);
        this.itemClickListenerSearchLocation = itemClickListenerSearchLocation;
    }

    public void setData(LocationSuggestionsItem locationSuggestionsItem) {

        Glide.with(iv_countryFlag.getContext()).
                load(locationSuggestionsItem.getCountryFlagUrl()).
                into(iv_countryFlag);

        tv_cityName.setText(locationSuggestionsItem.getName());
        tv_countryName.setText(locationSuggestionsItem.getCountryName());

        rl_rcLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListenerSearchLocation.onItemClick(getAdapterPosition(), locationSuggestionsItem);
            }
        });
    }

}
