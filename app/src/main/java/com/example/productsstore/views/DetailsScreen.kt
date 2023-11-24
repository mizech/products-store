package com.example.productsstore.views

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productsstore.models.Product
import com.example.productsstore.viewmodels.ProductVM
import com.example.productsstore.viewmodels.ProductsVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, product: Product) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Details",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center) },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                })
        }
    ) {
        OutlinedCard(modifier = Modifier.padding(6.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
            Column(modifier = Modifier
                // .fillMaxSize()
                .padding(horizontal = 15.dp)
                .padding(top = 70.dp, bottom = 15.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start) {
                Text(text = product.title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold)
                Text(text = "Category: ${product.category}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold)
                Text(text = product.description,
                    fontSize = 20.sp)
                Text(text = "Price: ${product.price} €",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp))
            }
        }
    }
}