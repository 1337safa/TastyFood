package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.helper

import okhttp3.Request

class RequestHelper {

    fun getRequestWithGetMethod(url: String): Request {
        return Request.Builder()
            .url(url)
            .get()
            .build()
    }

}