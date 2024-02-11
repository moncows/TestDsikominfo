package com.shiend.testdiskominfo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
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

}