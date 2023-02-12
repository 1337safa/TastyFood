package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category

import java.io.Serializable

data class FoodByCategoryItem(

    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val databaseIdFood: String?

): Serializable