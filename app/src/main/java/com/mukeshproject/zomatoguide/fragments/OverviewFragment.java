package com.mukeshproject.zomatoguide.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.mukeshproject.zomatoguide.R;
import com.mukeshproject.zomatoguide.activities.MainActivity;
import com.mukeshproject.zomatoguide.api.ApiClient;
import com.mukeshproject.zomatoguide.api.Network;
import com.mukeshproject.zomatoguide.listeners.LooperPreparedListener;
import com.mukeshproject.zomatoguide.model.BackgroundThread;
import com.mukeshproject.zomatoguide.restaurantpage.ResponseRestaurantInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OverviewFragment extends Fragment implements LooperPreparedListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int res_id = 0;
    private ImageView iv_restaurantImage_restinfo, iv_back_restinfo;
    private TextView tv_rating_restinfo, tv_restaurantName_restinfo, tv_cuisines_restinfo, tv_timing_restinfo;
    private boolean isLooperPrepared;
    private BackgroundThread thread;

    private String mParam1;
    private String mParam2;

    public OverviewFragment() {
        // Required empty public constructor
    }


    public static OverviewFragment newInstance(String param1, String param2) {
        OverviewFragment fragment = new OverviewFragment();
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
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        getBundleData();
//        if(isLooperPrepared) {
//            thread.addTaskToQueue(new Runnable() {
//                @Override
//                public void run() {
//                    fetchServer();
//                }
//            });
//        }
        fetchServer();
    }

    private void getBundleData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            res_id = bundle.getInt("res_id", 0);
        }
        Log.d("Mukesh res_Id", String.valueOf(res_id));
    }

    private void fetchServer() {

        ApiClient apiClient = Network.getRetrofitInstance().create(ApiClient.class);
        Call<ResponseRestaurantInfo> call = apiClient.getRestaurantPageInfo(18737018, "f372e6f364b53f71036e6f5662ecfa99");

        call.enqueue(new Callback<ResponseRestaurantInfo>() {
            @Override
            public void onResponse(Call<ResponseRestaurantInfo> call, Response<ResponseRestaurantInfo> response) {

                ResponseRestaurantInfo responseRestaurantInfo = response.body();
                setData(responseRestaurantInfo);
            }

            @Override
            public void onFailure(Call<ResponseRestaurantInfo> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setData(ResponseRestaurantInfo responseRestaurantInfo) {

        Glide.with(getContext()).
                load(responseRestaurantInfo.getFeaturedImage()).
                into(iv_restaurantImage_restinfo);

        tv_rating_restinfo.setText(responseRestaurantInfo.getUserRating().getAggregateRating() + "/5"
                + "\n" + responseRestaurantInfo.getAllReviewsCount() + "REVIEWS");

        tv_restaurantName_restinfo.setText(responseRestaurantInfo.getName());
        tv_cuisines_restinfo.setText(responseRestaurantInfo.getCuisines());
        tv_timing_restinfo.setText("Opens At: " + responseRestaurantInfo.getTimings());
    }


    private void initViews(View view) {

        iv_restaurantImage_restinfo = view.findViewById(R.id.iv_restaurantImage_restinfo);
        tv_rating_restinfo = view.findViewById(R.id.tv_rating_restinfo);
        tv_restaurantName_restinfo = view.findViewById(R.id.tv_restaurantName_restinfo);
        tv_cuisines_restinfo = view.findViewById(R.id.tv_cuisines_restinfo);
        tv_timing_restinfo = view.findViewById(R.id.tv_timing_restinfo);

        thread = new BackgroundThread("dataBase_Thread", this);
        thread.start();

        iv_back_restinfo = view.findViewById(R.id.iv_back_restinfo);

        iv_back_restinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onLooperPrepared() {
        isLooperPrepared = true;
    }
}