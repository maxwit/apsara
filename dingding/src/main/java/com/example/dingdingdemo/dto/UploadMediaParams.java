package com.example.dingdingdemo.dto;

/**
 * Created by nicolezhao
 * On 1/9/18.
 */
public class UploadMediaParams extends BaseParams {
    private String type;

    private String media;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
