package com.mukeshproject.zomatoguide.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.activities.MainActivity;
import com.mukeshproject.zomatoguide.adapter.ListOfReviews_Rc_Adapter;
import com.mukeshproject.zomatoguide.api.ApiClient;
import com.mukeshproject.zomatoguide.api.Network;
import com.mukeshproject.zomatoguide.listeners.LooperPreparedListener;
import com.mukeshproject.zomatoguide.listofreviews.ResponseReviews;
import com.mukeshproject.zomatoguide.model.BackgroundThread;
import com.mukeshproject.zomatoguide.restaurantpage.ResponseRestaurantInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReviewsFragment extends Fragment implements LooperPreparedListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final int res_id = 18737018;
    private final int phNumber = 0;
    private ImageView iv_back_reviewFrag, iv_call_reviewFrag;
    private TextView tv_restaurantNameTopBar, tv_restaurantName_reviewsFrag,
            tv_quickBites_reviewsFrag, tv_locality_reviewsFrag, tv_timimgs_reviewsFrag,
            tv_timeStatus_reviewFrag, tv_costfor2_reviewsFrag, rating, ratingCount;
    private RecyclerView rc_Reviews;
    private boolean isLooperPrepared;
    private ResponseReviews responseReviews = new ResponseReviews();
    private ResponseRestaurantInfo responseRestaurantInfo = new ResponseRestaurantInfo();
    private BackgroundThread thread;
    private String mParam1;
    private String mParam2;

    public ReviewsFragment() {
        // Required empty public constructor
    }


    public static ReviewsFragment newInstance(String param1, String param2) {
        ReviewsFragment fragment = new ReviewsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        fetchServer();

//        if(isLooperPrepared) {
//            thread.addTaskToQueue(new Runnable() {
//                @Override
//                public void run() {
//                    fetchServer();
//                }
//            });
//        }
    }

    private void initViews(View view) {

        iv_back_reviewFrag = view.findViewById(R.id.iv_back_reviewFrag);
        iv_back_reviewFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        tv_restaurantNameTopBar = view.findViewById(R.id.tv_restaurantNameTopBar);

        tv_restaurantName_reviewsFrag = view.findViewById(R.id.tv_restaurantName_reviewsFrag);
        tv_quickBites_reviewsFrag = view.findViewById(R.id.tv_quickBites_reviewsFrag);
        tv_locality_reviewsFrag = view.findViewById(R.id.tv_locality_reviewsFrag);

        tv_timeStatus_reviewFrag = view.findViewById(R.id.tv_timeStatus_reviewsFrag);
        tv_timimgs_reviewsFrag = view.findViewById(R.id.tv_timimgs_reviewsFrag);
        tv_costfor2_reviewsFrag = view.findViewById(R.id.tv_costfor2_reviewsFrag);
        iv_call_reviewFrag = view.findViewById(R.id.iv_call_reviewFrag);

        rating = view.findViewById(R.id.rating);
        ratingCount = view.findViewById(R.id.ratingCount);
        rc_Reviews = view.findViewById(R.id.rc_Reviews);


        thread = new BackgroundThread("dataBase_Thread", this);
        thread.start();
    }

    private void fetchServer() {

        ApiClient apiClient = Network.getRetrofitInstance().create(ApiClient.class);

        Call<ResponseRestaurantInfo> call1 = apiClient.getRestaurantPageInfo(res_id, "f372e6f364b53f71036e6f5662ecfa99");
        call1.enqueue(new Callback<ResponseRestaurantInfo>() {
            @Override
            public void onResponse(Call<ResponseRestaurantInfo> call, Response<ResponseRestaurantInfo> response) {
                responseRestaurantInfo = response.body();
                setRecyclerData(responseRestaurantInfo);
            }

            @Override
            public void onFailure(Call<ResponseRestaurantInfo> call, Throwable t) {

            }
        });

        Call<ResponseReviews> call = apiClient.getRestaurantReviews(res_id, "f372e6f364b53f71036e6f5662ecfa99");

        call.enqueue(new Callback<ResponseReviews>() {
            @Override
            public void onResponse(Call<ResponseReviews> call, Response<ResponseReviews> response) {
                responseReviews = response.body();
                setRecyclerData(responseReviews);
            }

            @Override
            public void onFailure(Call<ResponseReviews> call, Throwable t) {

            }
        });


    }

    private void setRecyclerData(ResponseReviews responseReviews) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rc_Reviews.setLayoutManager(linearLayoutManager);

        ListOfReviews_Rc_Adapter reviews_rc_adapter = new ListOfReviews_Rc_Adapter(responseReviews.getUserReviews());
        rc_Reviews.setAdapter(reviews_rc_adapter);
    }

    private void setRecyclerData(ResponseRestaurantInfo responseRestaurantInfo) {

        tv_restaurantNameTopBar.setText(responseRestaurantInfo.getName());

        tv_restaurantName_reviewsFrag.setText(responseRestaurantInfo.getName());
        tv_quickBites_reviewsFrag.setText(responseRestaurantInfo.getCuisines());
        tv_locality_reviewsFrag.setText(responseRestaurantInfo.getLocation().getLocalityVerbose());

        if (responseRestaurantInfo.getIsDeliveringNow() == 0) {
            tv_timeStatus_reviewFrag.setText("Closed -");
        } else {
            tv_timeStatus_reviewFrag.setText("Open -");
        }
        tv_timimgs_reviewsFrag.setText(responseRestaurantInfo.getTimings());
        tv_costfor2_reviewsFrag.setText("Cost for two - ₹" + responseRestaurantInfo.getAverageCostForTwo() + " (approx.)");
        rating.setText(responseRestaurantInfo.getUserRating().getAggregateRating());

        ratingCount.setText(responseRestaurantInfo.getAllReviewsCount() + "REVIEWS");

    }

    @Override
    public void onLooperPrepared() {
        isLooperPrepared = true;
    }
}