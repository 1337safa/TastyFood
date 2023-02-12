package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.local_database.sql

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository.DatabaseSql

class FavouriteFoodByCategoryInLocalDataBaseUseCase(

    private val databaseSql: DatabaseSql

) {

    fun insertFoodByCategory(foodByCategoryItem: FoodByCategoryItem) {
        databaseSql.insertFoodByCategory(foodByCategoryItem)
    }

    fun getFavouriteFoodByCategory(): ArrayList<FoodByCategoryItem>? {
        return databaseSql.getFavouriteFoodByCategory()
    }

    fun deleteFavouriteFoodByDatabaseFoodId(databaseIdFood: String?) {
        databaseSql.deleteFavouriteFoodByDatabaseFoodId(databaseIdFood)
    }

}