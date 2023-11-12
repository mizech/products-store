package com.example.productsstore.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.productsstore.ProductServices
import com.example.productsstore.RetrofitInstance
import com.example.productsstore.models.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductVM: ViewModel() {
    var product = mutableStateOf<Product>(Product())
        private set

    fun loadProduct(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var temp = RetrofitInstance.retrofit
                .create(ProductServices::class.java)
                .getProduct(id)
            withContext(Dispatchers.Main) {
                product.value = temp
            }
        }
    }
}