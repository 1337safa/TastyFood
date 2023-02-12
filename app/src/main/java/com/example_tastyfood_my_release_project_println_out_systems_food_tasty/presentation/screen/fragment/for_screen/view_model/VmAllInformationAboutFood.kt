package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_id.FoodById
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp.FoodByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VmAllInformationAboutFood(

    private val foodByIdUseCase: FoodByIdUseCase

): ViewModel() {

    private val foodByIdMutableLiveData = MutableLiveData<FoodById>()
    val foodByIdLiveData: LiveData<FoodById> = this.foodByIdMutableLiveData

    fun getFoodById(id: String) {

        viewModelScope.launch(Dispatchers.IO) {

            val result = foodByIdUseCase.getFoodByIdOrNull(id)
            result?.let {
                foodByIdMutableLiveData.postValue(it)
            }

        }

    }

}