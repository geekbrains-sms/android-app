package com.geekbrains.geekbrainsprogect.data.api;

import android.text.TextUtils;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.ui.auth.model.AuthToken;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product_list.model.Category;


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
import retrofit2.http.Body;
import retrofit2.http.Path;

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
        HttpLoggingInterceptor interceptorLog = new HttpLoggingInterceptor();
        interceptorLog.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (!TextUtils.isEmpty(token)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(token);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                httpClient.addInterceptor(interceptorLog);

                builder.client(httpClient.build());
            }
        }
        api = builder.build().create(IApiService.class);
    }

    public Single<Response<AuthToken>>authUser(String login, String password)
    {
        return auth.authUser(new UserRequest(login, password)).subscribeOn(Schedulers.io());
    }
    public Single<List<Product>>getProductList()
    {
        return api.getProductList().subscribeOn(Schedulers.io());
    }
    public Single<Response<String>>addProduct(Product product)
    {
        return api.addProduct(product);
    }
    public Single<Response<String>>deleteProductById(long id)
    {
        return api.deleteProductById(id);
    }
    public Single<Response<String>>deleteAllProduct()
    {
        return api.deleteAllProduct();
    }
    public Single<Response<ResponseBody>>editProduct(Product product)
    {
        return api.editProduct(product);
    }
    public Single<Response<List<Category>>>getCategoryList()
    {
        return api.getCategoryList();
    }
    public Single<Response<Category>>getCategoryById(Long id)
    {
        return api.getCategoryById(id);
    }
    public Single<Response<String>>deleteCategoryById(long id)
    {
        return api.deleteCategoryById(id);
    }
    public Single<Response<List<String>>>addCategory(Category category)
    {
        return api.addCategory(category);
    }
    public Single<Response<List<String>>>editCategory(@Body Category category)
    {
        return api.editCategory(category);
    }
}
