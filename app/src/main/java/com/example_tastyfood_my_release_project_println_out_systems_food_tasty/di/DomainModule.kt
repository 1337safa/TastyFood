package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.di

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.local_database.sql.FavouriteFoodByCategoryInLocalDataBaseUseCase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.local_database.sql.OpenCloseSqlDatabaseLocalDataBaseUseCase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp.AllFoodCategoriesUseCase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp.FoodByCategoryUseCase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp.FoodByIdUseCase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp.FoodByNameFromNetworkUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<FoodByIdUseCase> {
        FoodByIdUseCase(
            okHttpNetwork = get()
        )
    }

    factory<AllFoodCategoriesUseCase> {
        AllFoodCategoriesUseCase(
            okHttpNetwork = get()
        )
    }

    factory<FoodByCategoryUseCase> {
        FoodByCategoryUseCase(
            okHttpNetwork = get()
        )
    }

    factory<FoodByNameFromNetworkUseCase> {
        FoodByNameFromNetworkUseCase(
            okHttpNetwork = get()
        )
    }

    factory<FavouriteFoodByCategoryInLocalDataBaseUseCase> {
        FavouriteFoodByCategoryInLocalDataBaseUseCase(
            databaseSql = get()
        )
    }

    factory<OpenCloseSqlDatabaseLocalDataBaseUseCase> {
        OpenCloseSqlDatabaseLocalDataBaseUseCase(
            databaseSql = get()
        )
    }

}