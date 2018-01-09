package com.example.dingdingdemo;

import com.alibaba.fastjson.JSON;

/**
 * Created by nicolezhao
 * On 1/8/18.
 */
public class SendMsgParams extends BaseParams{
//    private String session;
//    private String method;
//    private String timestamp;
//    private String format;
//    private String v;
//    private String partner_id;
//    private Boolean simplify;
//
//    private String[] userid_list;
//    private Number[] dept_id_list;
//    private String agent_id;
//    private String msgtype;
//    private Boolean to_all_user;
//    private JSON msgcontent;
    private String access_token;
    private String touser;
    private String toparty;
    private String agentid;
    private String msgtype;
    private Text text;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
