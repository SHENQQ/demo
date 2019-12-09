package com.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InterruptedIOException;

@Slf4j
public class RetryIntercepter implements Interceptor {

    private int maxRetry;

    private int retryNum = 0;

    private int retryInterval = 2000;

    public RetryIntercepter(int maxRetry) {
        this.maxRetry = maxRetry;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);
        while (response == null || !response.isSuccessful() && retryNum < maxRetry) {
            try {
                Thread.sleep(retryInterval);
            } catch ( final InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }

            retryNum++;
            log.error("RetryIntercepter error retryNum={}", retryNum);

            response = doRequest(chain, request);
        }

        return response;
    }


    private Response doRequest( Chain chain,  Request request) {
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return response;
    }
}