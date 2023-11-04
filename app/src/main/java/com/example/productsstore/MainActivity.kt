package com.example.productsstore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productsstore.ui.theme.ProductsStoreTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsStoreTheme {
                var products = remember {
                    mutableStateListOf<Product>()
                }
                val navController = rememberNavController()

                LaunchedEffect(products) {
                    var temp = RetrofitInstance.retrofit
                        .create(ProductService::class.java)
                        .getProducts()
                    withContext(Dispatchers.Main) {
                        for (product in temp) {
                            products.add(product)
                        }
                    }
                }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        ),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // MainScreen(products = products)
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(products = products,
                                    navController = navController)
                        }
                        composable("details/{title}") {
                            val title = it.arguments?.getString("title") ?: ""
                            DetailsScreen(title = title, navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(products: MutableList<Product>, navController: NavController) {
    var nextTitle = remember {
        mutableStateOf("")
    }
    Column {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextField(
                value = nextTitle.value,
                onValueChange = {
                    nextTitle.value = it
                },
                trailingIcon = {
                    Icon(Icons.Default.Clear,
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                nextTitle.value = ""
                            })
                }
            )
            Button(onClick = {
                products.add(
                    Product(1, nextTitle.value, 19.99,
                        "Default Description", "Category 01",
                        "image01", Rating(1.0, 2.0)
                    ))
                nextTitle.value = ""
            }) {
                Text(text = "Add")
            }
        }

        LazyColumn(modifier = Modifier) {
            items(products) { product ->
                Column(modifier = Modifier
                    .padding(bottom = 10.dp)
                    .clickable {
                        navController.navigate("details/${product.title}")
                    }) {
                    Text(product.title, fontWeight = FontWeight.Bold)
                    Text(product.description)
                }
            }
        }
    }
}
