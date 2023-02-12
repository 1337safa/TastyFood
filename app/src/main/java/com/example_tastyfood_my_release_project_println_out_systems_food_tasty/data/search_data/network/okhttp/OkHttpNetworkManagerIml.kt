package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.constant.TypesOfLinksTheMealDb
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.helper.GsonHelper
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.helper.HttpLoggingInterceptorHelper
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.helper.OkHttpClientHelper
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.network.okhttp.helper.RequestHelper
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategory
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_id.FoodById
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_name.FoodByName
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_category.AllFoodCategories
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository.OkHttpNetwork
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

const val NULL_FROM_SERVER_IF_FOOD_IS_NOT_FOUND = "{\"meals\":null}"

class OkHttpNetworkManagerIml(

    private val httpLoggingInterceptorHelper: HttpLoggingInterceptorHelper,
    private val okHttpClientHelper: OkHttpClientHelper,
    private val requestHelper: RequestHelper,
    private val gsonHelper: GsonHelper

) : OkHttpNetwork {

    private val gson = gsonHelper.getGson()

    private val loggingInterceptor: HttpLoggingInterceptor = httpLoggingInterceptorHelper
        .getLoggingInterceptorWithABodyLevel()

    private val okHttpClient: OkHttpClient = okHttpClientHelper
        .getOkhttpClient(loggingInterceptor)

    override fun getAllFoodCategoriesOrNull(): AllFoodCategories? {

        val request = requestHelper
            .getRequestWithGetMethod(
                TypesOfLinksTheMealDb.LINK_FOR_ALL_FOOD_CATEGORIES
            )

        val newCall: Call = okHttpClient.newCall(request)
        val result = newCall.execute()

        if (result.isSuccessful) {

            if (result.body != null) {
                val resultString = result.body!!.string()
                val resultAsFoodCategory =
                    gson.fromJson(resultString, AllFoodCategories::class.java)
                return resultAsFoodCategory
            }

        }

        return null
    }

    override fun getFoodByCategoryOrNull(category: String): FoodByCategory? {

        val request = requestHelper.getRequestWithGetMethod(
            TypesOfLinksTheMealDb.LINK_FOR_FOOD_BY_CATEGORY + category
        )

        val newCall = okHttpClient.newCall(request)
        val result: Response = newCall.execute()

        if (result.isSuccessful) {

            if (result.body != null) {
                val resultString = result.body!!.string()
                val resultAsFoodByCategory = gson.fromJson(resultString, FoodByCategory::class.java)
                return resultAsFoodByCategory
            }

        }

        return null
    }

    override fun getFoodByNameOrNull(name: String): FoodByName? {

        val request = requestHelper.getRequestWithGetMethod(
            TypesOfLinksTheMealDb.LINK_FOR_FOOD_BY_NAME + name
        )

        val newCall = okHttpClient.newCall(request)
        val result = newCall.execute()

        if (result.isSuccessful) {

            if (result.body != null) {
                val resultString = result.body!!.string()
                val resultAsFoodByName = gson.fromJson(resultString, FoodByName::class.java)
                return resultAsFoodByName
            }

        }

        return null
    }

    override fun getFoodByIdOrNull(id: String): FoodById? {

        val request = requestHelper.getRequestWithGetMethod(
            TypesOfLinksTheMealDb.LINK_FOR_FOOD_BY_ID + id
        )

        val newCall = okHttpClient.newCall(request)
        val result = newCall.execute()

        if (result.isSuccessful) {

            if (result.body != null) {
                val resultString = result.body!!.string()
                val resultAsFoodById = gson.fromJson(resultString, FoodById::class.java)
                return resultAsFoodById
            }

        }

        return null
    }

}