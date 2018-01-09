package com.example.dingdingdemo;

/**
 * Created by nicolezhao
 * On 1/8/18.
 */
public class GetDeptUsersParams extends BaseParams{
    private String access_token;

    private Integer department_id;

    public GetDeptUsersParams(String access_token, Integer department_id) {
        this.access_token = access_token;
        this.department_id = department_id;
    }
}
