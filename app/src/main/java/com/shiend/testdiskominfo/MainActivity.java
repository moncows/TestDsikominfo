package com.shiend.testdiskominfo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shiend.testdiskominfo.model.DataVideo;
import com.shiend.testdiskominfo.model.VideoResp;
import com.shiend.testdiskominfo.network.Services;
import com.shiend.testdiskominfo.network.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity {

    private FrameLayout frameLayout;
    private Fragment frm;

    private Services services;
    private ImageView btnHomePage, btnMenu1, btnMenu2, btnMenu3, btnMenu4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        services = Utils.getClient().create(Services.class);
        services.getVideo().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                DataVideo dataVideo = (DataVideo) new Gson().fromJson(response.body().toString(), DataVideo.class);
                Log.e("LogTest", "onResponse: " + response.body().toString());
                Log.e("LogTest", "onResponse: " + dataVideo.getVideo());
                List<VideoResp> list = dataVideo.getVideo();
                showData(list);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("LogTest", "onFailure: " + t.getMessage());

            }
        });

        frameLayout = findViewById(R.id.frameLayout);
        frm = new HomeFragment();
        loadFragment(frm);

        initView();
        initClick();

    }

    private void initView() {
        btnHomePage = findViewById(R.id.btHomePage);
        btnMenu1 = findViewById(R.id.btnMenu1);
        btnMenu2 = findViewById(R.id.btnMenu2);
        btnMenu3 = findViewById(R.id.btnMenu3);
        btnMenu4 = findViewById(R.id.btnMenu4);

    }

    private void initClick() {
        btnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frm = new HomeFragment();
                loadFragment(frm);
            }
        });

        btnMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frm = new FragmentMenu1();
                loadFragment(frm);
            }
        });


        btnMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frm = new FragmentMenu2();
                loadFragment(frm);
            }
        });
        btnMenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frm = new FragmentMenu3();
                loadFragment(frm);
            }
        });

        btnMenu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frm = new FragmentMenu4();
                loadFragment(frm);
            }
        });

    }

    private void showData(List<VideoResp> list) {
        for (int i = 0; i < list.size(); i++) {
            Log.e("LogTest", "Data: " + list.get(i).getCaption());
        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        frm = new HomeFragment();
        loadFragment(frm);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        frm = new HomeFragment();
        loadFragment(frm);
    }
}