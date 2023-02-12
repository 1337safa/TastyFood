package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategory
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_id.FoodById
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_name.FoodByName
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_category.AllFoodCategories

interface OkHttpNetwork {

    fun getAllFoodCategoriesOrNull(): AllFoodCategories?
    fun getFoodByCategoryOrNull(category: String): FoodByCategory?
    fun getFoodByNameOrNull(name: String): FoodByName?
    fun getFoodByIdOrNull(id: String): FoodById?

}