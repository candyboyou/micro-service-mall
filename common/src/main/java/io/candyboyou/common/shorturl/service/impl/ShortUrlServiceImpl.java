package io.candyboyou.common.shorturl.service.impl;

import io.candyboyou.common.shorturl.service.ShortUrlService;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ShortUrlServiceImpl implements ShortUrlService {

    private static final String BASE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Logger log = LoggerFactory.getLogger("io.candyboyou.common.ShortUrlService");

    private static final String URL = "https://www.baidu.com/s";

    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
            .build();

    private String signature;

    private ShortUrlServiceImpl() {}

    public ShortUrlServiceImpl(String signature)
    {
        this.signature = signature;
    }

    @Override
    public String genShortUrl(String url) {
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("signature", signature));
        pairs.add(new BasicNameValuePair("action", "shorturl"));
        pairs.add(new BasicNameValuePair("url", url));
        pairs.add(new BasicNameValuePair("format", "json"));
        HttpGet httpGet = new HttpGet(String.format("%s?%s", URL, URLEncodedUtils.format(pairs, StandardCharsets.UTF_8)));
        httpGet.setConfig(requestConfig);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            if (HttpStatus.SC_OK != httpResponse.getStatusLine().getStatusCode()) {
                log.error("http status: {}", httpResponse.getStatusLine().getStatusCode());
                return null;
            }
            String response = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            if (StringUtils.isBlank(response)) {
                log.error("response is blank");
                return null;
            }
//            JSONObject jsonObject = JSON.parseObject(response);
//            int statusCode = jsonObject.getIntValue("statusCode");
//            if (200 != statusCode) {
//                log.error("response is : {}", response);
                return null;
//            }
//            String shortUrl = jsonObject.getString("shorturl");
//            if (StringUtils.isBlank(shortUrl)) {
//                log.error("response is: {}", response);
//                return null;
//            }
//            return shortUrl;
//            return null;
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
        return null;
    }

    @Override
    public String expandShortUrl(String shortUrl) {
        return null;
    }

    public static void main(String[] args) {
        ShortUrlServiceImpl shortUrlService = new ShortUrlServiceImpl();
        String s = shortUrlService.genShortUrl("ie=utf-8&f=3&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E6%B5%8B%E8%AF%95%E7%BD%91%E9%80%9F&fenlei=256&rsv_pq=d41ed647000b7a38&rsv_t=1c6fu%2FcFgCd9PnjwKGY%2B%2FUbExViTxle0kMzUzi9EH6%2BifrNEdyxC%2BQho%2B5s&rqlang=cn&rsv_dl=ts_0&rsv_enter=1&rsv_sug3=8&rsv_sug1=6&rsv_sug7=100&rsv_sug2=0&rsv_btype=i&prefixsug=ceshi&rsp=0&inputT=2980&rsv_sug4=2980");
//        String s = shortUrlService.genShortUrl("https://www.douyu.com/276200");
        System.out.println(s);
    }
}
