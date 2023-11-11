package com.example.productsstore

import com.example.productsstore.models.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductServices {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: String): Product
}