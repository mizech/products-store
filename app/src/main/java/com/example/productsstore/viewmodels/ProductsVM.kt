package com.example.productsstore.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.productsstore.ProductServices
import com.example.productsstore.RetrofitInstance
import com.example.productsstore.models.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ProductsVM: ViewModel() {
    var products = mutableStateListOf<Product>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            var temp = RetrofitInstance.retrofit
                .create(ProductServices::class.java)
                .getProducts()
            withContext(Dispatchers.Main) {
                for (product in temp) {
                    products.add(product)
                }
            }
        }
    }
}