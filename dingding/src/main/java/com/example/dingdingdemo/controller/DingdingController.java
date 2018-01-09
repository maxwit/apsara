package com.example.dingdingdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dingdingdemo.dto.*;
import com.example.dingdingdemo.util.HttpClientUtils;
import com.example.dingdingdemo.util.MapUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
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

    private String getDeptListUrl = "https://oapi.dingtalk.com/department/list";
    private String getDeptUserListUrl = "https://oapi.dingtalk.com/user/simplelist";
    private String sendMsgUrl = "https://oapi.dingtalk.com/message/send";
    private String createMicroappUrl = "https://oapi.dingtalk.com/microapp/create";
    private String uploadMediaUrl = "https://oapi.dingtalk.com/media/upload";


    /**
     * 获取企业accessToken
     */
    private String getCorpAccessToken() {
        GetCorpTokenParams getCorpTokenParams = new GetCorpTokenParams(CORPID, CORPSECRET);
        HttpUriRequest httpUriRequest = createGetRequest(getCorpTokenParams, URL_CORPTKEN);
        GetTokenResult getTokenResult1 = HttpClientUtils.executeJsonResult(httpUriRequest, GetTokenResult.class);

        String corp_access_token = getTokenResult1.getAccess_token();
        return corp_access_token;
    }

    private HttpUriRequest createGetRequest(BaseParams baseParams, String uri) {
        RequestBuilder builder = RequestBuilder.get().setHeader(jsonHeader).setUri(uri);
        Map<String, String> param = MapUtil.objectToMap(baseParams);
        param.keySet().forEach(key -> builder.addParameter(new BasicNameValuePair(key, param.get(key))));
        return builder.build();
    }

    private HttpUriRequest createPostRequest(BaseParams baseParams, String uri) {
        String params = JSON.toJSONString(baseParams);
        return RequestBuilder.post().setHeader(jsonHeader).setUri(uri)
                .setEntity(new StringEntity(params, Charset.forName("utf-8"))).build();
    }

    private HttpPost createMediaPostRequest(UploadMediaParams baseParams, String uri, File file) {
        HttpPost httpPost = new HttpPost(uri);
        HttpEntity httpEntity = MultipartEntityBuilder.create().addPart("media",
                new FileBody(file, ContentType.APPLICATION_OCTET_STREAM, file.getName())).addTextBody("type", baseParams.getType()).build();
        httpPost.setEntity(httpEntity);
        return httpPost;
    }

    @RequestMapping(value = "/getDept")
    public String getDeptList() {
        GetDeptListParams getDeptListParams = new GetDeptListParams(getCorpAccessToken());
        HttpUriRequest httpUriRequest = createGetRequest(getDeptListParams, getDeptListUrl);
        Object obj = HttpClientUtils.executeJsonResult(httpUriRequest, Object.class);
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
        if (sendMsgParams == null) {
            return "error";
        }

//        sendMsgParams.setAccess_token(getCorpAccessToken());
        HttpUriRequest httpUriRequest = createPostRequest(sendMsgParams, sendMsgUrl + "?access_token=" + getCorpAccessToken());
        Object obj = HttpClientUtils.executeJsonResult(httpUriRequest, Object.class);
        return JSON.toJSONString(obj);
    }

    @RequestMapping(value = "/createMicroapp", method = RequestMethod.POST)
    public String createMicroapp(@RequestBody CreateMicroappParams createMicroappParams) {
        HttpUriRequest httpUriRequest = createPostRequest(createMicroappParams, createMicroappUrl + "?access_token=" + getCorpAccessToken());
        Object obj = HttpClientUtils.executeJsonResult(httpUriRequest, Object.class);
        return JSON.toJSONString(obj);

        //return {"errcode":0,"agentid":158364254,"errmsg":"ok"}
    }

    @RequestMapping(value = "/uploadMedia", method = RequestMethod.POST)
    public String uploadMedia(@RequestParam(value = "type") String type, @RequestParam(value = "media") MultipartFile file) {
        UploadMediaParams uploadMediaParams = new UploadMediaParams();
        uploadMediaParams.setType(type);
        File file1 = new File("/Users/nicolezhao/test.jpg");
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpPost httpPost = createMediaPostRequest(uploadMediaParams, uploadMediaUrl + "?access_token=" + getCorpAccessToken(), file1);
        CloseableHttpResponse response;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            response = httpClient.execute(httpPost, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                return "error";
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");

                JSONObject result = JSON.parseObject(resultStr);
                if (result.getInteger("errcode") == 0) {
                    // 成功
                    result.remove("errcode");
                    result.remove("errmsg");
                    return result.toJSONString();
                } else {
                    return result.toJSONString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
        //return {"media_id":"@lADPBY0V4t6fxCjNARrNAUY","created_at":1515511088064,"type":"image"}
    }
}
