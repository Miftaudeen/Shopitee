package com.example.shopitee.models

data class CategoryModel(val image: String = "https://res.cloudinary.com/emkaydauda/image/upload/v1584222322/shopping-min.jpg", val title: String) {

    constructor() : this(title = "")

}