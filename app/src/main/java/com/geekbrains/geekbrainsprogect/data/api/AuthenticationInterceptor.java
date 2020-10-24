package com.geekbrains.geekbrainsprogect.data.api;

import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        if(AppData.token != null)
        {
            builder.addHeader("Authorization","Bearer " + AppData.token);
        }
        Request request = builder.build();
        return chain.proceed(request);
    }
}
