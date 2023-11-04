package com.example.productsstore

import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}