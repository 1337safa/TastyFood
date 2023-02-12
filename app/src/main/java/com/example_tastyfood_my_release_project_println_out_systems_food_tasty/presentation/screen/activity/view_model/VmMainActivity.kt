package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.activity.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.local_database.sql.FavouriteFoodByCategoryInLocalDataBaseUseCase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.local_database.sql.OpenCloseSqlDatabaseLocalDataBaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VmMainActivity(

    private var favouriteFoodByCategoryInLocalDatabase: FavouriteFoodByCategoryInLocalDataBaseUseCase,
    private var openCloseSqlDatabaseUseCaseLocalDataBase: OpenCloseSqlDatabaseLocalDataBaseUseCase

): ViewModel() {

    fun onOpenDatabase() {
        openCloseSqlDatabaseUseCaseLocalDataBase.onOpenDatabase()
    }

    fun insertFoodByCategory(foodByCategoryItem: FoodByCategoryItem) {

        try {
            viewModelScope.launch(Dispatchers.IO) {
                favouriteFoodByCategoryInLocalDatabase.insertFoodByCategory(foodByCategoryItem)
            }
        } catch (e: Exception) {
            throw e
        }

    }

    fun deleteFavouriteFoodByDatabaseFoodId(databaseIdFood: String?) {

        try {
            favouriteFoodByCategoryInLocalDatabase.deleteFavouriteFoodByDatabaseFoodId(databaseIdFood)
        } catch (e: Exception) {
            throw e
        }

    }

    override fun onCleared() {
        super.onCleared()
        openCloseSqlDatabaseUseCaseLocalDataBase.onCloseDatabase()
    }

}