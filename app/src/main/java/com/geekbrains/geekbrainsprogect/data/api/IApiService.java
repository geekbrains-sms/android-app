package com.geekbrains.geekbrainsprogect.data.api;

import com.geekbrains.geekbrainsprogect.data.Contractor;
import com.geekbrains.geekbrainsprogect.data.Role;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.geekbrains.geekbrainsprogect.ui.product.model.ProductTransaction;
import com.geekbrains.geekbrainsprogect.ui.product.model.Unit;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiService {

    @GET("/api/v1/products")
    Single<List<Product>> getProductList();
    @POST("/api/v1/products")
    Single<Response<Product>>addProduct(@Body Product product);
    @DELETE("/api/v1/products/{id}")
    Single<Response<String>>deleteProductById(@Path("id")long id);
    @DELETE("/api/v1/products")
    Single<Response<String>>deleteAllProduct();
    @PUT("/api/v1/products")
    Single<Response<ResponseBody>>editProduct(@Body Product product);

    @GET("/api/v1/categories")
    Single<Response<List<Category>>>getCategoryList();
    @GET("/api/v1/categories/{id}")
    Single<Response<Category>>getCategoryById(@Path("id")long id);
    @DELETE("/api/v1/categories/{id}")
    Single<Response<String>>deleteCategoryById(@Path("id")long id);
    @POST("/api/v1/categories")
    Single<Response<Category>>addCategory(@Body Category category);
    @PUT("/api/v1/categories")
    Single<Response<List<String>>>editCategory(@Body Category category);

    @GET("/api/v1/transactions/supply")
    Single<Response<List<ProductTransaction>>>getAllSupplyProductTransactions();
    @GET("/api/v1/transactions/shipment")
    Single<Response<List<ProductTransaction>>>getAllShipmentProductTransactions();

    @Headers("Content-Type: application/json")
    @POST("/api/v1/transactions/supply")
    Single<Response<List<ProductTransaction>>>addSupplyTransactions(@Body ProductTransaction productTransaction);

    @Headers("Content-Type: application/json")
    @POST("/api/v1/transactions/shipment")
    Single<Response<List<ProductTransaction>>>addShipmentTransactions(@Body ProductTransaction productTransaction);

    @GET("/api/v1/transactions/product/{id}")
    Single<Response<List<ProductTransaction>>>getProductTransactionById(@Path("id")long id);
//    @GET("/api/v1/transactions/author")
//    Single<Response<List<ProductTransaction>>>getProductTransactionsByAuthor(@Query("author") String author);
//    @GET("/api/v1/transactions/products/data")
//    Single<Response<List<ProductTransaction>>>getProductTransactionsByData(@Query("date") Date date);

    @GET("/api/v1/units")
    Single<Response<List<Unit>>>getAllUnits();


    @GET("/api/v1/contractors")
    Single<Response<List<Contractor>>>getAllContractors();
    @GET("/api/v1/contractors/providers/{productId}")
    Single<Response<List<Contractor>>>getProvidersByProductId(@Path("productId")long id);

    @Headers("Content-Type: application/json")
    @POST("/api/v1/contractors")
    Single<Response<Contractor>>addContractor(@Body Contractor contractor);
    @DELETE("/api/v1/contractors/{id}")
    Single<Response<Contractor>>deleteContractorById(@Path("id")long id);
    @PUT("/api/v1/contractors")
    Single<Response<Contractor>>editContractor(@Body Contractor contractor);

    @GET("/api/v1/funds")
    Single<Response<List<Fund>>>getAllFunds();
    @GET("/api/v1/funds/product/{var}")
    Single<Response<Fund>>getFundsByProductId(@Path("var")long id);


    @GET("/api/v1/users")
    Single<Response<List<User>>>getAllUsers();
    @GET("/api/v1/users/{id}")
    Single<Response<User>>getUserById(@Path("id")long id);
    @PUT("/api/v1/users/{id}")
    Single<Response<ResponseBody>>editUser(@Path("id")long id, @Body User user);
    @POST("/api/v1/users")
    Single<Response<User>>addUser(@Body User user);
    @DELETE("/api/v1/users/{id}")
    Single<Response<ResponseBody>>deleteUser(@Path("id")long id);

    @GET("/api/v1/roles")
    Single<Response<List<Role>>>getAllRoles();














}
