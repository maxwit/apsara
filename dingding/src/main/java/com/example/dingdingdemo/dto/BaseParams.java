package com.example.dingdingdemo.dto;

import java.io.Serializable;

/**
 * Created by Lucyar on 2017/4/25.
 */
public class BaseParams implements Serializable {

    private String errcode;//返回码
    private String errmsg;//对返回码的文本描述内容

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
