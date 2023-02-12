package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }

}