package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.interfaces

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem

interface ClickOnFoodByCategoryItemAdapter {

    fun clickOnFoodByCategoryItemFromRcvAdapterFoodByCategory(foodByCategoryItem: FoodByCategoryItem)
    fun clickOnBtnFavouriteFoodFromRcvAdapterFoodByCategory(foodByCategoryItem: FoodByCategoryItem)

}