package com.example.productsstore.models

import com.example.productsstore.Rating

data class Product(
    val id: Int = -1,
    var title: String = "",
    var price: Double = 0.0,
    var description: String = "",
    var category: String = "",
    var image: String = "",
    var rating: Rating = Rating(rate = 0.0, count = 0.0)
)