package com.example.dingdingdemo.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nicolezhao
 * On 1/8/18.
 */
public class HttpClientUtils {
    protected static HttpClient httpClient = HttpClientFactory.createHttpClient(100, 10);
    private static Map<String, HttpClient> httpClient_mchKeyStore = new HashMap();

    public HttpClientUtils() {
    }

    public static void init(int maxTotal, int maxPerRoute) {
        httpClient = HttpClientFactory.createHttpClient(maxTotal, maxPerRoute);
    }

    public static void initMchKeyStore(String mch_id, String keyStoreFilePath) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(keyStoreFilePath));
            keyStore.load(instream, mch_id.toCharArray());
            instream.close();
            HttpClient httpClient = HttpClientFactory.createKeyMaterialHttpClient(keyStore, mch_id, new String[]{"TLSv1"});
            httpClient_mchKeyStore.put(mch_id, httpClient);
        } catch (NoSuchAlgorithmException | IOException | CertificateException | KeyStoreException var5) {
            var5.printStackTrace();
        }

    }

    public static HttpResponse execute(HttpUriRequest request) {
        try {
            return httpClient.execute(request);
        } catch (IOException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static <T> T execute(HttpUriRequest request, ResponseHandler<T> responseHandler) {
        try {
            return httpClient.execute(request, responseHandler);
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static <T> T executeJsonResult(HttpUriRequest request, Class<T> clazz) {
        return execute(request, JsonResponseHandler.createResponseHandler(clazz));
    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;

        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();

            String line;
            for (in = new BufferedReader(new InputStreamReader(connection.getInputStream())); (line = in.readLine()) != null; result = result + line) {
                ;
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception var15) {
                var15.printStackTrace();
            }

        }

        return result;
    }
}
