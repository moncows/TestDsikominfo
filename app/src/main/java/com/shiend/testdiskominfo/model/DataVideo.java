package com.shiend.testdiskominfo.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class DataVideo {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("video")
    @Expose
    private List<VideoResp> video;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<VideoResp> getVideo() {
        return video;
    }

    public void setVideo(List<VideoResp> video) {
        this.video = video;
    }

}