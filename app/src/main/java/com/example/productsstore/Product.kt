package com.example.productsstore

data class Product(
    val id: Int,
    var title: String,
    var price: Double,
    var description: String,
    var category: String,
    var image: String,
    var rating: Rating
)