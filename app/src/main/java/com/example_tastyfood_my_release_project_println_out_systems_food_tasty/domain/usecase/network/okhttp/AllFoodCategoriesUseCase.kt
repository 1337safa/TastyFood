package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_category.AllFoodCategories
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository.OkHttpNetwork

class AllFoodCategoriesUseCase(

    private val okHttpNetwork: OkHttpNetwork

) {

    fun getAllFoodCategoriesOrNull(): AllFoodCategories? {
        return okHttpNetwork.getAllFoodCategoriesOrNull()
    }

}