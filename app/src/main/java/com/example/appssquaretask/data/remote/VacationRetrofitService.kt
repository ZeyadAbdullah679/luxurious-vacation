package com.example.appssquaretask.data.remote

import com.example.appssquaretask.data.model.auth.SignInResponse
import com.example.appssquaretask.data.model.auth.SignUpResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*

interface VacationRetrofitService {

    @FormUrlEncoded
    @POST("api/sign_up")
    suspend fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<SignUpResponse>

    @FormUrlEncoded
    @POST("api/login")
    suspend fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<SignInResponse>

    @GET("api/restaurants")
    suspend fun listRestaurants(
        @Query("skip") skip: Int = 0,
        @Query("search") search: String? = null
    ): Response<SignUpResponse>

    @GET("api/restaurants/{id}")
    suspend fun restaurantDetails(
        @Path("id") id: String
    ): Response<SignUpResponse>

    @GET("api/products")
    suspend fun listProducts(
        @Query("skip") skip: Int = 0,
        @Query("search") search: String? = null,
        @Query("restaurant_id") restaurantId: String? = null
    ): Response<SignUpResponse>

    @GET("api/products/{id}")
    suspend fun productDetails(
        @Path("id") id: String
    ): Response<SignUpResponse>

    @DELETE("api/products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Response<SignUpResponse>

    @FormUrlEncoded
    @POST("api/products")
    suspend fun createProduct(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("price") price: String,
        @Field("quantity") quantity: String
    ): Response<SignUpResponse>

}