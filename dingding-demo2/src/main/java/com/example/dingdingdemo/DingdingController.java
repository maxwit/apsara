package com.example.dingdingdemo;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by nicolezhao
 * On 1/8/18.
 */
@RestController
public class DingdingController {
    private static String CORPID = "ding3b77bc2efd0768f8";
    private static String CORPSECRET = "o4FEmJZmFwiiw_IO74LXikuucD1HR4aLlIMq0fG5hWpxKitlOTAbaYHgu8rr0cXP";
    public static final String URL_CORPTKEN = "https://oapi.dingtalk.com/gettoken";

    protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

    protected static HttpClient httpClient = HttpClientFactory.createHttpClient(100, 10);
    private String getDeptListUrl = "https://oapi.dingtalk.com/department/list";
    private String getDeptUserListUrl = "https://oapi.dingtalk.com/user/simplelist";
    private String sendMsgUrl = "https://oapi.dingtalk.com/message/send";


    /**
     * 获取企业accessToken
     */
    public static String getCorpAccessToken() {
        GetCorpTokenParams getCorpTokenParams = new GetCorpTokenParams(CORPID, CORPSECRET);
        HttpUriRequest httpUriRequest = createGetRequest(getCorpTokenParams, URL_CORPTKEN);
        GetTokenResult getTokenResult1 = HttpClientUtils.executeJsonResult(httpUriRequest, GetTokenResult.class);

        String corp_access_token = getTokenResult1.getAccess_token();
        return corp_access_token;
    }

    public static HttpUriRequest createGetRequest(BaseParams baseParams, String uri) {
        RequestBuilder builder = RequestBuilder.get().setHeader(jsonHeader).setUri(uri);
        Map<String, String> param = MapUtil.objectToMap(baseParams);
        param.keySet().forEach(key -> builder.addParameter(new BasicNameValuePair(key, param.get(key))));
        return builder.build();
    }

    public static HttpUriRequest createPostRequest(BaseParams baseParams, String uri) {
        String params = JSON.toJSONString(baseParams);
        return RequestBuilder.post().setHeader(jsonHeader).setUri(uri)
                .setEntity(new StringEntity(params, Charset.forName("utf-8"))).build();
    }

    @RequestMapping(value = "/getDept")
    public String getDeptList() {
//        String accessToken = getCorpAccessToken();
        GetDeptListParams getDeptListParams = new GetDeptListParams(getCorpAccessToken());
        HttpUriRequest httpUriRequest = createGetRequest(getDeptListParams, getDeptListUrl);
        GetDeptResult obj = HttpClientUtils.executeJsonResult(httpUriRequest, GetDeptResult.class);
        return JSON.toJSONString(obj);
    }

    @RequestMapping(value = "/getDeptUsers")
    public String getDeptUsers(HttpServletRequest request, @PathParam(value = "deptId") String deptId) {

//        String accessToken = "134113ff3ab43ee58492385139b4df67";
        GetDeptUsersParams getDeptListParams = new GetDeptUsersParams(getCorpAccessToken(), Integer.parseInt(deptId));
        HttpUriRequest httpUriRequest = createGetRequest(getDeptListParams, getDeptUserListUrl);
        Object obj = HttpClientUtils.executeJsonResult(httpUriRequest, Object.class);
        return JSON.toJSONString(obj);
    }
// 0508666134747703  zijiao zhao
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public String sendMsg(@RequestBody SendMsgParams sendMsgParams) {
        if(sendMsgParams == null) {
            return "error";
        }

        sendMsgParams.setAccess_token(getCorpAccessToken());
        HttpUriRequest httpUriRequest = createPostRequest(sendMsgParams, sendMsgUrl);
        Object obj = HttpClientUtils.executeJsonResult(httpUriRequest, Object.class);
        return JSON.toJSONString(obj);
    }
}
