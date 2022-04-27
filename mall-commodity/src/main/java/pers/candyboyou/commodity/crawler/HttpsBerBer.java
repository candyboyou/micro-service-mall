package pers.candyboyou.commodity.crawler;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.*;
import java.util.logging.Logger;

public class HttpsBerBer {

    public static void main(String[] args) {
        String url = "https://app.m.mi.com/v2/cate/index";
        Map<String,Object> params = new HashMap<>();
        doPost(url, params);
    }

    public static JsonObject doPost(String url, Map<String,Object> params) {
        String result = null;
        SSLContext sslcontext = null;
        try {
            sslcontext = createIgnoreVerifySSL();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        //设置协议http和https对应的处理socket链接工厂的对象
        assert sslcontext != null;
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);
        //创建自定义的httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();
//		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); // 连接超时时间
//		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 90000);  // 数据传输时间
        HttpPost post = null;
        post = new HttpPost(url);
        post.setHeader("Content-type", "application/x-www-form-urlencoded");
        post.setHeader("Cookie", "client_id=client_id=180100041089;channel_id=1044.1000;serviceToken=7O5IpyaoM6DUQDvGJElkL3zCYcXVxl4iY0F2IFJMnw9ecWlgKTgdbuXv5wP6DPJdmMzL2WUuWidJhhDQKpRyjRj2NPcjHsTv3IKR6VK%2BzFL45TBiAaBtEx9ZOA8H%2FcIjvYy2r212yQkobqLN92wRZ3PhU9dpsUGZVKvWPcWquaGj8XBrmk5NL0ixTo6t04nb6D6Xq1bBjaT6YDTS6SufcSqkZk86WwG6bIQUiwH%2FApw%3D;nickName=dW5kZWZpbmVk;avatar=undefined;lat=31.212356567382812;lng=121.47685241699219;share_user=;wx_business_user=;share_channel=;platform=mac;model=MacBookAir10,1;brand=MacBookAir10,1;version=7.0.8;system=macOS 12.3.0;SDKVersion=2.13.2;mishop_wx_version=4.29.6;masid=;gdt_vid=;weixinadinfo=;user_token=RL5VfCGQc9LdBFmT7GCenhxUc2uobMxH9XsdRjyrLOLBmxnaNg6OeOs0prFVkA18jpe0ipMIQBGcuo5B50OSi+EmTfaL0hi5KPCY/SsoTRGaloXcGymd2ljIM9lDQnwGZvVDgbx6hZ3rso+Wrd5+SATvt39C/UT6WcQe84y1BxM=;sign_token=1ypfYhQfTwM1fY5ynD6vFz2zZF2LqwdKib%2B0%2FI6gCP65fqD8WRqsIWX%2FIiaUIOgP;rawData=eyJuaWNrTmFtZSI6IuW+ruS/oeeUqOaItyIsImdlbmRlciI6MCwibGFuZ3VhZ2UiOiIiLCJjaXR5IjoiIiwicHJvdmluY2UiOiIiLCJjb3VudHJ5IjoiIiwiYXZhdGFyVXJsIjoiaHR0cHM6Ly90aGlyZHd4LnFsb2dvLmNuL21tb3Blbi92aV8zMi9QT2dFd2g0bUlITzRuaWJIMEtsTUVDTmpqR3hRVXEyNFpFYUdUNHBvQzZpY1JpY2NWR0tTeVh3aWJjUHE0QldtaWFJR3VHMWljd3hhUVg2Z3JDOVZlbVpvSjhyZy8xMzIifQ==;signature=1c96af68939844f44036d3c99fbf44f75e67ea09;rebate_invite_code=;mstuid=e2bff45e-56d2-4cb1-ae65-d39378d6c18e-1641822800600;client_version=4.29.6;");
        post.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E217 MicroMessenger/6.8.0(0x16080000) NetType/WIFI Language/en Branch/Br_trunk MiniProgramEnv/Mac");
        post.setHeader("Referer", "https://servicewechat.com/wx17ea87763491620f/1427/page-frame.html");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for(String key:keySet){
            list.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
            HttpResponse response = (HttpResponse) httpClient.execute(post);
            result = response2String(response);

//            File jsonFile = new File("/Users/candyboy/IdeaProjects/micro-service-mall/mall-commodity/src/main/java/pers/candyboyou/commodity/crawler/category.json");
//            FileReader fileReader = new FileReader(jsonFile);
//            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
//            int ch = 0;
//            StringBuffer sb = new StringBuffer();
//            while ((ch = reader.read()) != -1) {
//                sb.append((char) ch);
//            }
//            fileReader.close();
//            reader.close();
//            String jsonStr = sb.toString();

            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
            gsonBuilder.setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            return jsonObject;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭
        httpClient.getConnectionManager().shutdown();
        return null;
    }

    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }

    public static String response2String(HttpResponse response){
        HttpEntity entity = response.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return body;
    }
}
