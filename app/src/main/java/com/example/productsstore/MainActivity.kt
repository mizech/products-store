package com.example.productsstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productsstore.models.Product
import com.example.productsstore.ui.theme.ProductsStoreTheme
import com.example.productsstore.viewmodels.ProductVM
import com.example.productsstore.viewmodels.ProductsVM
import com.example.productsstore.views.DetailsScreen
import com.example.productsstore.views.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsStoreTheme {
                val navController = rememberNavController()
                val productsVM by viewModels<ProductsVM>()
                val productVM by viewModels<ProductVM>()
                var searchTerm by remember {
                    mutableStateOf("")
                }

                NavHost(navController = navController, startDestination = Routes.MAIN.name) {
                    composable(Routes.MAIN.name) {
                        MainScreen(products = productsVM.products.filter
                            {
                                ".*${searchTerm.lowercase()}.*"
                                    .toRegex()
                                    .find(it.title.lowercase()) != null
                            }
                                as MutableList<Product>,
                            navController = navController,
                            searchTerm = searchTerm,
                            onChange = { searchTerm = it})
                    }
                    composable("${Routes.DETAILS.name}/{id}") {
                        val id = it.arguments?.getString("id") ?: ""
                        productVM.loadProduct(id = id)
                        DetailsScreen(navController = navController,
                            product = productVM.product.value)
                    }
                }
            }
        }
    }
}

