//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.dingdingdemo.util;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import java.security.*;

public class HttpClientFactory {
    public HttpClientFactory() {
    }

    public static HttpClient createHttpClient(int maxTotal, int maxPerRoute) {
        try {
            SSLContext sslContext = SSLContexts.custom().useSSL().build();
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
            poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
            return HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager).setSSLSocketFactory(sf).build();
        } catch (NoSuchAlgorithmException | KeyManagementException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static HttpClient createKeyMaterialHttpClient(KeyStore keystore, String keyPassword, String[] supportedProtocols) {
        try {
            SSLContext sslContext = SSLContexts.custom().useSSL().loadKeyMaterial(keystore, keyPassword.toCharArray()).build();
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext, supportedProtocols, (String[]) null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            return HttpClientBuilder.create().setSSLSocketFactory(sf).build();
        } catch (NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException | KeyManagementException var5) {
            var5.printStackTrace();
            return null;
        }
    }
}
