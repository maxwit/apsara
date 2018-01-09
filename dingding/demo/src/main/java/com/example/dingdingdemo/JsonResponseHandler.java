//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.dingdingdemo;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class JsonResponseHandler {
    private static Map<String, ResponseHandler<?>> map = new HashMap();

    public JsonResponseHandler() {
    }

    public static <T> ResponseHandler<T> createResponseHandler(Class<T> clazz) {
        if (map.containsKey(clazz.getName())) {
            return (ResponseHandler)map.get(clazz.getName());
        } else {
            ResponseHandler<T> responseHandler = (response) -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    String str = EntityUtils.toString(entity);
                    System.out.println("HttpResponse = " + str);
                    return JSON.parseObject(new String(str.getBytes("iso-8859-1"), "utf-8"), clazz);
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            map.put(clazz.getName(), responseHandler);
            return responseHandler;
        }
    }
}
