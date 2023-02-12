package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_name.FoodByNameItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository.OkHttpNetwork

class FoodByNameFromNetworkUseCase(

    private val okHttpNetwork: OkHttpNetwork

) {

    fun getFoodByNameOrNull(name: String): List<FoodByNameItem>? {

        val result = okHttpNetwork.getFoodByNameOrNull(name)
        result?.meals?.let { it ->
            return it
        }

        return null
    }

}