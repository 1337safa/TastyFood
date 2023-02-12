package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.OkHttpNetworkManagerIml
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_id.FoodById
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository.OkHttpNetwork

class FoodByIdUseCase(

    private val okHttpNetwork: OkHttpNetwork

) {

    fun getFoodByIdOrNull(id: String): FoodById? {
        return okHttpNetwork.getFoodByIdOrNull(id)
    }

}