package com.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkHttpUtils {

    private static volatile OkHttpUtils instance = null;

    private OkHttpClient okHttpClient;

    private OkHttpUtils() {
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectionPool(pool())
                .connectTimeout(6, TimeUnit.SECONDS)
                .readTimeout(6, TimeUnit.SECONDS)
                .writeTimeout(6, TimeUnit.SECONDS)
                .addInterceptor(new RetryIntercepter(3))
                .build();
    }


    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }

        return instance;
    }

    public String assembleUrl(Map<String, Object> params) {
        if (params != null) {
            return encodeUrl(params);
        } else {
            return "";
        }
    }

    public String assembleUrl(String url, Map<String, Object> params) {
        if (params != null) {
            if (url.contains("?")) {
                return url + "&" + encodeUrl(params);
            } else {
                return url + "?" + encodeUrl(params);
            }
        } else {
            return url;
        }
    }

    private Headers assembleHeaders( Map<String, String> headersParams) {
        Headers headers;
        Headers.Builder headersBuilder = new Headers.Builder();
        if (headersParams != null) {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key;
            while (iterator.hasNext()) {
                key = iterator.next();
                headersBuilder.add(key, headersParams.get(key));
            }
        }

        headers = headersBuilder.build();
        return headers;
    }

    private ConnectionPool pool() {
        return new ConnectionPool(400, 5, TimeUnit.MINUTES);
    }

    /**
     * encode 字符串
     *
     * @param content
     * @return
     */
    public static String encode(String content) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }

        try {
            return URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将Key-value转换成用&号链接的URL查询参数形式。
     *
     * @param parameters
     * @return
     */
    public static String encodeUrl( Map<String, Object> parameters) {
        if (parameters == null) {
            return "";
        }

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String key : parameters.keySet()) {
            list.add(key);
        }

        Collections.sort(list);
        for (String key : list) {
            if (first)
                first = false;
            else
                sb.append("&");
            if (parameters.get(key) != null) {
                try {
                    sb.append(URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(String.valueOf(parameters.get(key)), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public String doGet( String reqUrl) {
        return doGet(reqUrl, null, null);
    }

    public String doGet( String reqUrl, Map<String, String> headersParams, Map<String, Object> params) {
        Request request = new Request.Builder()
                .url(assembleUrl(reqUrl, params))
                .headers(assembleHeaders(headersParams))
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                log.error("doGet Fail, code={}, url={}, params={}, message={}", response.code(), reqUrl, params, response.message());
            }
        } catch (Exception e) {
            log.error("doGet Exception, url={}, params={}, message={}", reqUrl, params, e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return null;
    }

    public Map<String, Object> doPost( String url, Map<String, String> headersParams,  Map<String, String> params) {
        Map<String, Object> resultMap = new HashMap<>();

        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .headers(assembleHeaders(headersParams))
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                resultMap.put("status", 1);
                resultMap.put("message", response.body() != null ? response.body().string() : "");
            } else {
                int code = response.code();
                String message = response.message();

                log.error("doPost Fail, code={}, url={}, params={}, message={}", code, url, params, message);

                resultMap.put("status", 0);
                resultMap.put("message", message);
            }
        } catch (Exception e) {
            log.error("doPost Exception, url={}, params={}, message={}", url, params, e.getMessage());

            resultMap.put("status", 0);
            resultMap.put("message", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return resultMap;
    }

    public Response doPostForm(String url, Map<String, String> headersParams,  Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, String.valueOf(params.get(key)));
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .headers(assembleHeaders(headersParams))
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return response;
        } catch (Exception e) {
            log.error("doPost2 Fail, url={}, params={}, message={}", url, params, e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return null;
    }

    public Response doPost2( String url, Map<String, String> headersParams,  Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, String.valueOf(params.get(key)));
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .headers(assembleHeaders(headersParams))
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return response;
        } catch (Exception e) {
            log.error("doPost2 Fail, url={}, params={}, message={}", url, params, e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return null;
    }

    public void doPost2(String url, Map<String, String> headersParams,  Map<String, Object> params, ReqCallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, String.valueOf(params.get(key)));
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .headers(assembleHeaders(headersParams))
                .build();

        Response response = null;
        try {
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.error("doPost2 Fail, call={}, ioException={}", call, e.getMessage());

                    if (callBack != null) {
                        callBack.onReqFailed(e.toString());
                    }
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    log.info("doPost2 onResponse success {}", response);

                    if (callBack != null) {
                        callBack.onReqSuccess(response.body() != null ? response.body().string() : "");
                    }
                }
            });
        } catch (Exception e) {
            log.error("doPost2 Exception, url={}, headersParams={}, params={}, message={}", url, headersParams, params, e.getMessage());
            if (callBack != null) {
                callBack.onReqFailed(e.getMessage());
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public Map<String, Object> doPostXML( String url, Map<String, String> headersParams,  String xml) {
        Map<String, Object> resultMap = new HashMap<>();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = new Request.Builder()
                .url(url)
                .headers(assembleHeaders(headersParams))
                .post(requestBody)
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                resultMap.put("status", 1);
                resultMap.put("message", response.body() != null ? response.body().string() : "");
            } else {
                int code = response.code();
                String message = response.message();

                log.error("doPostXML Fail, code={}, url={}, xml={}, message={}", code, url, xml, message);

                resultMap.put("status", 0);
                resultMap.put("message", message);
            }
        } catch (Exception e) {
            log.error("doPostXML Exception, url={}, xml={}, message={}", url, xml, e.getMessage());

            resultMap.put("status", 0);
            resultMap.put("message", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return resultMap;
    }

    public void doPostJsonAsyn(String url, Map<String, String> headersParams, JSONObject jsonObject, ReqCallBack callBack) {
        log.info("post the json:{} to url:{}", jsonObject, url);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toJSONString());
        Request request = new Request.Builder()
                .get()
                .url(url)
                .headers(assembleHeaders(headersParams))
                .post(requestBody)
                .build();
        Response response = null;
        try {
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.error("doPostJsonAsyn Fail, call={}, ioException={}", call, e.getMessage());

                    if (callBack != null) {
                        callBack.onReqFailed(e.toString());
                    }
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    log.info("doPostJsonAsyn onResponse success {}", response);

                    if (callBack != null) {
                        callBack.onReqSuccess(response.toString());
                    }
                }
            });
        } catch (Exception e) {
            log.error("doPostJsonAsyn Exception, url={}, json={}, message={}", url, jsonObject, e.getMessage());

            if (callBack != null) {
                callBack.onReqFailed(e.getMessage());
            }

        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public String doPostJsonSyn(String url, Map<String, String> headersParams, JSONObject jsonObject) {
        log.info("post the json:{} to url:{}", jsonObject, url);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toJSONString());
        Request request = new Request.Builder()
                .url(url)
                .headers(assembleHeaders(headersParams))
                .post(requestBody)
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return response.body() != null ? response.body().string() : "";
        } catch (Exception e) {
            log.error("doPostJsonSyn Exception, url={}, json={}, message={}", url, jsonObject, e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return "";
    }
}