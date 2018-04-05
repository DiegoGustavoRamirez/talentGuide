package com.leones.talentguide.api;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class AuthenticationInterceptor implements Interceptor {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String NO_AUTHENTICATION = "No-authentication";
    private String userName;
    private String password;
    private String accessToken;

    public AuthenticationInterceptor(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public AuthenticationInterceptor(String token) {
        this.accessToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        if (!TextUtils.isEmpty(accessToken)) {
            requestBuilder.header(HEADER_AUTHORIZATION, "Bearer " + accessToken);
        } else {

            if (request.header(NO_AUTHENTICATION) == null) {
                // needs credentials
                requestBuilder.header(HEADER_AUTHORIZATION, Credentials.basic(userName, password));
            }
        }

        return chain.proceed(requestBuilder.build());
    }
}
