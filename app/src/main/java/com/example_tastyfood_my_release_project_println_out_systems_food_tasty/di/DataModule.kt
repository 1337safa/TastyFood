package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.di

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.local.sql.DatabaseSqlImpl
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.OkHttpNetworkManagerIml
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.helper.GsonHelper
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.helper.HttpLoggingInterceptorHelper
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.helper.OkHttpClientHelper
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.helper.RequestHelper
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository.DatabaseSql
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository.OkHttpNetwork
import org.koin.dsl.module

val dataModule = module {

    single<DatabaseSql> {
        DatabaseSqlImpl(get())
    }

    single<OkHttpNetwork> {
        OkHttpNetworkManagerIml(
            httpLoggingInterceptorHelper = get(), okHttpClientHelper = get(),
            requestHelper = get(), gsonHelper = get()
        )
    }

    single<GsonHelper> {
        GsonHelper()
    }

    single<HttpLoggingInterceptorHelper> {
        HttpLoggingInterceptorHelper()
    }

    single<OkHttpClientHelper> {
        OkHttpClientHelper()
    }

    single<RequestHelper> {
        RequestHelper()
    }

}