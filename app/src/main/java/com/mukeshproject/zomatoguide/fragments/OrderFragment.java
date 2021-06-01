package com.mukeshproject.zomatoguide.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshproject.zomatoguide.CustomComparator;
import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.activities.RestaurantInfoActivity;
import com.mukeshproject.zomatoguide.adapter.ListOfRestaurants_Rc_Adapter;
import com.mukeshproject.zomatoguide.api.ApiClient;
import com.mukeshproject.zomatoguide.api.Network;
import com.mukeshproject.zomatoguide.listeners.ItemClickListenerSearchRestaurant;
import com.mukeshproject.zomatoguide.listofrestaurants.ResponseListOfRestaurants;
import com.mukeshproject.zomatoguide.listofrestaurants.Restaurant;
import com.mukeshproject.zomatoguide.sharedpreferences.PreferenceHelper;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment implements View.OnClickListener, ItemClickListenerSearchRestaurant {

    private static final String PREF_CITY_NAME_KEY = "PREF_CITY_NAME_KEY";
    private static final String PREF_ENTITY_ID_KEY = "PREF_ENTITY_ID_KEY";
    private static final String PREF_RES_ID_KEY = "PREF_RES_ID_KEY";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private SearchView sv_searchRestaurantMain;
    private ImageView iv_notFound;
    private String q = "";
    private int entity_id = 0;
    private ApiClient apiClient;
    private ResponseListOfRestaurants responseListOfRestaurants;
    private ListOfRestaurants_Rc_Adapter listOfRestaurants_rc_adapter;
    private TextView filterAscending, filterDescending, rating3, rating4, tv_displayCity;
    private RecyclerView rc_listRestaurants;


    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPrefData();
        initViews(view);
        fetchServer(q, entity_id);

    }

    private void getPrefData() {
        q = PreferenceHelper.getCityNameFromPreference(getContext(), PREF_CITY_NAME_KEY);
        entity_id = PreferenceHelper.getEntityIdFromPreference(getContext(), PREF_ENTITY_ID_KEY);
    }

    private void initViews(View view) {

        tv_displayCity = view.findViewById(R.id.tv_displayCity);
        filterAscending = view.findViewById(R.id.filterAscending);
        filterDescending = view.findViewById(R.id.filterDescending);
        rating3 = view.findViewById(R.id.rating3_0);
        rating4 = view.findViewById(R.id.rating4_0);
        rc_listRestaurants = view.findViewById(R.id.rc_listRestaurants);
        iv_notFound = view.findViewById(R.id.iv_notFound);

        apiClient = Network.getRetrofitInstance().create(ApiClient.class);

        sv_searchRestaurantMain = view.findViewById(R.id.sv_searchRestaurantMain);
        sv_searchRestaurantMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchServer(newText, entity_id);
                return false;
            }
        });
        filterAscending.setOnClickListener(this);
        filterDescending.setOnClickListener(this);
        rating3.setOnClickListener(this);
        rating4.setOnClickListener(this);

    }

    private void fetchServer(String q, int entity_id) {

        Call<ResponseListOfRestaurants> call = apiClient.getRestaurantSearchResponse(q, entity_id, "city", "f372e6f364b53f71036e6f5662ecfa99");

        call.enqueue(new Callback<ResponseListOfRestaurants>() {
            @Override
            public void onResponse(@NotNull Call<ResponseListOfRestaurants> call, @NotNull Response<ResponseListOfRestaurants> response) {

                responseListOfRestaurants = response.body();
                setRecyclerAdapter(responseListOfRestaurants);

                if (responseListOfRestaurants.getResultsFound() == 0) {
                    iv_notFound.setVisibility(View.VISIBLE);
                } else {
                    iv_notFound.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseListOfRestaurants> call, @NotNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setRecyclerAdapter(ResponseListOfRestaurants responseListOfRestaurants) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rc_listRestaurants.setLayoutManager(linearLayoutManager);

        listOfRestaurants_rc_adapter = new ListOfRestaurants_Rc_Adapter(responseListOfRestaurants.getRestaurants(), this);
        rc_listRestaurants.setAdapter(listOfRestaurants_rc_adapter);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rc_listRestaurants.setLayoutManager(linearLayoutManager);

        listOfRestaurants_rc_adapter = new ListOfRestaurants_Rc_Adapter(responseListOfRestaurants.getRestaurants(), this);
        switch (id) {

            case R.id.filterAscending:

                Collections.sort(responseListOfRestaurants.getRestaurants(), new CustomComparator());
                break;

            case R.id.filterDescending:
                Collections.reverse(responseListOfRestaurants.getRestaurants());
                break;

            case R.id.rating3_0:
                break;

            case R.id.rating4_0:
                break;
        }
    }

    @Override
    public void onItemClick(int position, Restaurant restaurant) {

        Intent intent = new Intent(getContext(), RestaurantInfoActivity.class);
        PreferenceHelper.writeResIdToPreference(getContext(), PREF_RES_ID_KEY, Integer.parseInt(restaurant.getId()));
        startActivity(intent);
    }

}