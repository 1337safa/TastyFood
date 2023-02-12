package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.helper

import okhttp3.logging.HttpLoggingInterceptor

class HttpLoggingInterceptorHelper {

    fun getLoggingInterceptorWithABodyLevel(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

}