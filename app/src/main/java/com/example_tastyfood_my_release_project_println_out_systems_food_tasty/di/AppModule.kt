package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.di

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.activity.view_model.VmMainActivity
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.view_model.VmAllInformationAboutFood
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.view_model.VmMainContent
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {

    viewModel<VmMainActivity> {
        VmMainActivity(
            favouriteFoodByCategoryInLocalDatabase = get(), openCloseSqlDatabaseUseCaseLocalDataBase =  get()
        )
    }

    viewModel<VmMainContent> {
        VmMainContent(
            allFoodByCategoryUseCase = get(),
            foodByCategoryUseCase =  get()
        )
    }

    viewModel<VmAllInformationAboutFood> {
        VmAllInformationAboutFood(
            foodByIdUseCase = get()
        )
    }

}