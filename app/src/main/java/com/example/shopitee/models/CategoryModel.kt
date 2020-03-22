package com.example.shopitee.models

data class CategoryModel(val categoryIconLink: String = "https://res.cloudinary.com/emkaydauda/image/upload/v1584222322/shopping-min.jpg", val categoryName: String) {

    constructor() : this(categoryName = "")

}