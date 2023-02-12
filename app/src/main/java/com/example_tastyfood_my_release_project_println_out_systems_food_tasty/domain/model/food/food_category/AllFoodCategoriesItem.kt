package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_category

import java.io.Serializable

data class AllFoodCategoriesItem(

    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String

): Serializable