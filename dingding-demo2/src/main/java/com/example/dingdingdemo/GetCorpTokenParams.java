package com.example.dingdingdemo;

/**
 * Created by Lucyar on 2017/4/24.
 */
public class GetCorpTokenParams extends BaseParams{

    private String corpid;
    private String corpsecret;

    public GetCorpTokenParams() {
    }

    public GetCorpTokenParams(String corpid, String corpsecret) {
        this.corpid = corpid;
        this.corpsecret = corpsecret;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getCorpsecret() {
        return corpsecret;
    }

    public void setCorpsecret(String corpsecret) {
        this.corpsecret = corpsecret;
    }
}
