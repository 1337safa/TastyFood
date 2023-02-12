package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem

interface DatabaseSql {

    fun onOpenDatabase()
    fun insertFoodByCategory(foodByCategoryItem: FoodByCategoryItem)
    fun getFavouriteFoodByCategory(): ArrayList<FoodByCategoryItem>?
    fun deleteFavouriteFoodByDatabaseFoodId(databaseIdFood: String?)
    fun onCloseDatabase()

}