package com.geekbrains.geekbrainsprogect.data.dagger;

import com.geekbrains.geekbrainsprogect.data.api.AuthenticationInterceptor;
import com.geekbrains.geekbrainsprogect.data.api.service.AuthService;
import com.geekbrains.geekbrainsprogect.data.api.service.CategoryService;
import com.geekbrains.geekbrainsprogect.data.api.service.ContractorService;
import com.geekbrains.geekbrainsprogect.data.api.service.FundService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductTransactionService;
import com.geekbrains.geekbrainsprogect.data.api.service.RoleService;
import com.geekbrains.geekbrainsprogect.data.api.service.UnitService;
import com.geekbrains.geekbrainsprogect.data.api.service.UserActionService;
import com.geekbrains.geekbrainsprogect.data.api.service.UserService;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
public class NetworkModule {
    private String BASE_URL = "http://192.168.1.235:8189";
    public static final int TIMEOUT = 20;

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    AuthenticationInterceptor provideAuthorizationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, AuthenticationInterceptor authInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();


        builder.addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    @Singleton
    AuthService provideAuthService(Retrofit retrofit)
    {
        return retrofit.create(AuthService.class);
    }

    @Provides
    @Singleton
    UserService provideUserService(Retrofit retrofit)
    {
        return retrofit.create(UserService.class);
    }

    @Provides
    @Singleton
    CategoryService provideCategoryService(Retrofit retrofit) {
        return retrofit.create(CategoryService.class);
    }

    @Provides
    @Singleton
    ContractorService provideContractorService(Retrofit retrofit) {
        return retrofit.create(ContractorService.class);
    }

    @Provides
    @Singleton
    FundService provideFundService(Retrofit retrofit) {
        return retrofit.create(FundService.class);
    }

    @Provides
    @Singleton
    ProductService providerProductService(Retrofit retrofit) {
        return retrofit.create(ProductService.class);
    }

    @Provides
    @Singleton
    ProductTransactionService providerProductTransactionService(Retrofit retrofit) {
        return retrofit.create(ProductTransactionService.class);
    }

    @Provides
    @Singleton
    RoleService providerRoleService(Retrofit retrofit) {
        return retrofit.create(RoleService.class);
    }

    @Provides
    @Singleton
    UnitService providerUnitService(Retrofit retrofit) {
        return retrofit.create(UnitService.class);
    }

    @Provides
    @Singleton
    UserActionService providerUserActionService(Retrofit retrofit) {
        return retrofit.create(UserActionService.class);
    }

    @Provides
    @Singleton
    UserService providerUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }
}
