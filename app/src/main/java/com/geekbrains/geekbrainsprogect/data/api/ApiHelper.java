package com.geekbrains.geekbrainsprogect.data.api;

import android.text.TextUtils;
import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.ui.auth.presenter.AuthPresenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private static final String BASE_URL = "http://192.168.1.235:8189";
    private Retrofit.Builder builder;

    private IAuthService auth;
    private IApiService api;
    public ApiHelper()
    {
        builder = createBuilder();
        createAuthService();
    }

    private Retrofit.Builder createBuilder() {
//        Gson gson = new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
//                .setLenient()
//                .create();
        return builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    private void createAuthService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        builder.client(client.build());
        auth = builder.build().create(IAuthService.class);
    }
    public void createApiService(String token) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (!TextUtils.isEmpty(token)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(token);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
            }
        }
        api = builder.build().create(IApiService.class);
    }

    public Single<Response<ResponseBody>>authUser(String login, String password)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", login);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(AuthPresenter.TAG, jsonObject.toString());

        return auth.authUser(new UserRequest(login, password)).subscribeOn(Schedulers.io());
    }

    public Single<List<User>>requestAllUsers(){
        return api.getAllUsers().subscribeOn(Schedulers.io());
    }
    public Single<String>registerUser(String login, String password)
    {
        return api.postUser(login, password).subscribeOn(Schedulers.io());
    }
    public Single<String>deleteUser(String login)
    {
        return api.deleteUser(login).subscribeOn(Schedulers.io());
    }
    public Single<User>getUser(String username)
    {
        return api.getUser(username).subscribeOn(Schedulers.io());
    }
}
