package com.my.myproject.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * 获取天气信息
 *
 */
public class WeatherUtil {

    public static String getWeather(String region){
        return null;
    }

    public static void main(String[] args) throws Exception {
        String string = get("https://free-api.heweather.net/s6/weather/now?location=石家庄&key=d366b304a7834a3a8a847455122c3eec");
        System.out.println(string);
        String string2 = get("https://search.heweather.net/find?location=石家庄&key=d366b304a7834a3a8a847455122c3eec");
        System.out.println(string2);
    }
    /**
     * get请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String get(String url) throws Exception {
        String content = null;
        URLConnection urlConnection = new URL(url).openConnection();
        HttpURLConnection connection = (HttpURLConnection) urlConnection;
        connection.setRequestMethod("GET");
        // 连接
        connection.connect();
        // 得到响应码
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder bs = new StringBuilder();
            String l;
            while ((l = bufferedReader.readLine()) != null) {
                bs.append(l).append("\n");
            }
            content = bs.toString();
        }
        return content;
    }
}
