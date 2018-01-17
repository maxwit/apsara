package com.example.dingdingdemo.dto;

/**
 * Created by nicolezhao
 * On 1/8/18.
 */
public class GetDeptListParams extends BaseParams {
    private String access_token;

    public GetDeptListParams(String access_token) {
        this.access_token = access_token;
    }
}
