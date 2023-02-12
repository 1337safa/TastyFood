package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategory
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_name.FoodByNameItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_category.AllFoodCategories
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp.AllFoodCategoriesUseCase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp.FoodByCategoryUseCase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp.FoodByNameFromNetworkUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VmMainContent(

    private val allFoodByCategoryUseCase: AllFoodCategoriesUseCase,
    private val foodByCategoryUseCase: FoodByCategoryUseCase

) : ViewModel() {

    private val foodCategoryMutableLiveData = MutableLiveData<AllFoodCategories>()
    val foodCategoryLiveData: LiveData<AllFoodCategories> = foodCategoryMutableLiveData

    private val foodByCategoryMutableLiveData = MutableLiveData<FoodByCategory>()
    val foodByCategoryLiveData: LiveData<FoodByCategory> = foodByCategoryMutableLiveData

    private var latestFoodByCategoryName: String = ""

    fun getAllFoodByCategory(category: String) {

        if (category != latestFoodByCategoryName) {

            viewModelScope.launch(Dispatchers.IO) {
                val result: FoodByCategory? = foodByCategoryUseCase.getFoodByCategoryOrNull(category)
                result?.let { it ->
                    this@VmMainContent.foodByCategoryMutableLiveData.postValue(it)
                }
                latestFoodByCategoryName = category
            }

        }

    }

    fun getAllFoodCategories() {

        viewModelScope.launch(Dispatchers.IO) {
            val result: AllFoodCategories? = allFoodByCategoryUseCase.getAllFoodCategoriesOrNull()
            result?.let { it ->
                this@VmMainContent.foodCategoryMutableLiveData.postValue(it)
            }
        }

    }

}