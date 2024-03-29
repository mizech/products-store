package com.example.productsstore.views

import android.annotation.SuppressLint
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.productsstore.models.Product
import com.example.productsstore.Rating

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(products: MutableList<Product>,
               navController: NavController,
               searchTerm: String,
               onChange: (newValue: String) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "All Products available",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        })
    }) {
        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(value = searchTerm,
                onValueChange = {
                                onChange(it)
                },
                trailingIcon = {
                               Icon(Icons.Default.Clear,
                                   contentDescription = "",
                                   modifier = Modifier.clickable {
                                       onChange("")
                                   })
                },
                label = { Text("Search Product") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp, start = 6.dp, end = 6.dp))
            LazyColumn(modifier = Modifier.padding(vertical = 4.dp, horizontal = 5.dp)) {
                items(products) { product ->
                    OutlinedCard(modifier = Modifier.fillMaxSize().padding(4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
                        Column(modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .clickable {
                                navController.navigate("details/${product.id}")
                            }) {
                            Text(product.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(bottom = 8.dp))
                            Text(product.description,
                                maxLines = 5,
                                modifier = Modifier.padding(bottom = 16.dp))
                        }
                    }
                }
            }
        }
    }
}
