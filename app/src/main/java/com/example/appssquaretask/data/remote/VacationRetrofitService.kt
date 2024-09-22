package com.example.appssquaretask.data.remote

import com.example.appssquaretask.data.model.auth.SignInResponse
import com.example.appssquaretask.data.model.auth.SignUpResponse
import com.example.appssquaretask.data.model.restaurant.Restaurants
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface VacationRetrofitService {

    @FormUrlEncoded
    @POST("sign_up")
    suspend fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<SignUpResponse>

    @FormUrlEncoded
    @POST("login")
    suspend fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<SignInResponse>

    @GET("restaurants")
    suspend fun listRestaurants(
        @Query("skip") skip: Int = 0,
        @Query("search") search: String? = null
    ): Response<Restaurants>

    @GET("restaurants/{id}")
    suspend fun restaurantDetails(
        @Path("id") id: String
    ): Response<SignUpResponse>

    @GET("products")
    suspend fun listProducts(
        @Query("skip") skip: Int = 0,
        @Query("search") search: String? = null,
        @Query("restaurant_id") restaurantId: String? = null
    ): Response<SignUpResponse>

    @GET("products/{id}")
    suspend fun productDetails(
        @Path("id") id: String
    ): Response<SignUpResponse>

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Response<SignUpResponse>

    @FormUrlEncoded
    @POST("products")
    suspend fun createProduct(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("price") price: String,
        @Field("quantity") quantity: String
    ): Response<SignUpResponse>

}