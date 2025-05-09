package org.eu.tz95.tools.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author tz95
 * @project dnspod-update
 * @date 2025/5/8
 */
public class ConnectionUtil {

    private static HttpClient httpClient = HttpClient.newHttpClient();

    public static HttpResponse<String> sendRequest(String uri, String[] headerStr, String body){
        HttpResponse resp = null;
        try {
            if (body != null) {
                resp = httpClient.send(HttpRequest.newBuilder()
                        .uri(URI.create(uri))
                        .header(headerStr[0], headerStr[1])
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .build(),HttpResponse.BodyHandlers.ofString());
            }else {
                resp = httpClient.send(HttpRequest.newBuilder()
                        .uri(URI.create(uri))
                        .header(headerStr[0], headerStr[1])
                        .GET()
                        .build(), HttpResponse.BodyHandlers.ofString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        if (resp.statusCode() == 200) {
            return resp;
        } else {
            System.err.println("请求失败 - 状态码: " + resp.statusCode());
            System.err.println("响应内容: " + resp.body()); // 输出 API 错误详情
            return null;
        }
    }

    public static boolean testConnection(){
        HttpResponse<String> resp = null;
        try {
            resp = httpClient.send(HttpRequest.newBuilder()
                    .uri(URI.create("https://www.baidu.com")).build(), HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        if (resp.statusCode() == 200) {
            return true;
        } else {
            System.out.println("Failed to connect to the internet");
            return false;
        }
    }

}
