package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.alert.interfaces

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_name.FoodByNameItem

interface ClickOnSomeFoodByName {
    fun clickOnMoreFromRcvAdapterSearchFoodByNameAlertView(foodByNameItem: FoodByNameItem)
    fun clickOnCancelFromRcvAdapterSearchFoodByNameAlertView()
}