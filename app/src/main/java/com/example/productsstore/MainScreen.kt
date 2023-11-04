package com.example.productsstore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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
                modifier = Modifier.padding(top = 8.dp, bottom = 15.dp),
                onValueChange = {
                    nextTitle.value = it
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
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
