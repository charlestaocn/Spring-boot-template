package com.charles.template.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

/**
 * @author charles tao
 */


public class OkHttpUtils {


    public static final OkHttpClient HTTPCLIENT = new OkHttpClient.Builder()
            .build();

    public static <T> List<T> getList(String url, Class<T> type, Map<String, ?> params) {
        String response = execGet(url, params);
        return parseList(response, type);
    }

    private static String execGet(String url, Map<String, ?> params) {
        HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        for (Map.Entry<String, ?> entry : params.entrySet()) {
            if (entry.getValue() instanceof String value) {
                builder.addQueryParameter(entry.getKey(), value);
            } else if (entry.getValue() instanceof Number value) {
                builder.addQueryParameter(entry.getKey(), "" + value);
            } else {
                builder.addQueryParameter(entry.getKey(), JSON.toJSONString(entry.getValue()));
            }
        }

        return get((builder.build()));
    }


    public static <T> T get(String url, Class<T> clz, Map<String, String> params) {
        return parse(execGet(url, params), clz);
    }

    private static String synchronizedCall(Request request) {
        try (Response response = HTTPCLIENT.newCall(request).execute()) {
            return Optional.ofNullable(response.body()).map(body -> {
                try {
                    return body.string();
                } catch (IOException e) {
                    return "";
                }
            }).orElse("");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    public static String get(HttpUrl url) {
        Request request = new Request.Builder()
                .get()
                .url(url.url())
                .build();
        return synchronizedCall(request);
    }


    public static <T> T parse(String json, Class<T> clz) {
        return JSON.parseObject(json, clz);
    }

    public static <T> List<T> parseList(String json, Class<T> clz) {
        return Optional.ofNullable(JSON.parseArray(json, clz)).orElse(new ArrayList<>());
    }
}
