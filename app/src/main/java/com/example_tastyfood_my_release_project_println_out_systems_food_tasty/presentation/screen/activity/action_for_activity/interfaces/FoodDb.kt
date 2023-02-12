package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.activity.action_for_activity.interfaces

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem

interface FoodDb {

    fun insertFoodByCategoryToLocalDatabase(foodByCategoryItem: FoodByCategoryItem)
    fun getFavouriteFoodByCategoryFromLocalDatabase(): ArrayList<FoodByCategoryItem>?
    fun deleteFavouriteFoodByDatabaseFoodIdFromLocalDatabase(databaseIdFood: String?)

}