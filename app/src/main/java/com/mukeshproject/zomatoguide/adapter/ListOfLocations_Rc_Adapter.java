package com.mukeshproject.zomatoguide.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.listeners.ItemClickListenerSearchLocation;
import com.mukeshproject.zomatoguide.listoflocations.LocationSuggestionsItem;
import com.mukeshproject.zomatoguide.viewholder.ListOfLocations_Rc_ViewHolder;

import java.util.List;

public class ListOfLocations_Rc_Adapter extends RecyclerView.Adapter<ListOfLocations_Rc_ViewHolder> {

    private final List<LocationSuggestionsItem> locationSuggestionsItemsList;
    private final ItemClickListenerSearchLocation itemClickListenerSearchLocation;

    public ListOfLocations_Rc_Adapter(List<LocationSuggestionsItem> locationSuggestionsItemsList, ItemClickListenerSearchLocation itemClickListenerSearchLocation) {
        this.locationSuggestionsItemsList = locationSuggestionsItemsList;
        this.itemClickListenerSearchLocation = itemClickListenerSearchLocation;
    }

    @NonNull
    @Override
    public ListOfLocations_Rc_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_listoflocation_design, parent, false);
        return new ListOfLocations_Rc_ViewHolder(view, itemClickListenerSearchLocation);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOfLocations_Rc_ViewHolder holder, int position) {

        LocationSuggestionsItem locationSuggestionsItem = locationSuggestionsItemsList.get(position);
        holder.setData(locationSuggestionsItem);
    }

    @Override
    public int getItemCount() {
        return locationSuggestionsItemsList.size();
    }
}
