package com.example.productsstore.views

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
fun MainScreen(products: MutableList<Product>, navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "All Products available",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        })
    }) {
        LazyColumn(modifier = Modifier.padding(top = 65.dp)) {
            items(products) { product ->
                Column(modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .clickable {
                        navController.navigate("details/${product.id}")
                    }) {
                    Text(product.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp)
                    Text(product.description,
                        modifier = Modifier.padding(bottom = 15.dp))
                }
            }
        }
    }
}
