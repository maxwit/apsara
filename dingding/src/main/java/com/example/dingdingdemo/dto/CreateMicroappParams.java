package com.example.dingdingdemo.dto;

/**
 * Created by nicolezhao
 * On 1/9/18.
 */
public class CreateMicroappParams extends BaseParams {
    private String appIcon;
    private String appName;
    private String appDesc;
    private String homepageUrl;
    private String pcHomepageUrl;
    private String ompLink;

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public String getPcHomepageUrl() {
        return pcHomepageUrl;
    }

    public void setPcHomepageUrl(String pcHomepageUrl) {
        this.pcHomepageUrl = pcHomepageUrl;
    }

    public String getOmpLink() {
        return ompLink;
    }

    public void setOmpLink(String ompLink) {
        this.ompLink = ompLink;
    }
}
