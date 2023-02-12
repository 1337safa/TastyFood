package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategory
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository.OkHttpNetwork

class FoodByCategoryUseCase(

    private val okHttpNetwork: OkHttpNetwork

) {

    fun getFoodByCategoryOrNull(category: String): FoodByCategory? {
        return okHttpNetwork.getFoodByCategoryOrNull(category)
    }

}