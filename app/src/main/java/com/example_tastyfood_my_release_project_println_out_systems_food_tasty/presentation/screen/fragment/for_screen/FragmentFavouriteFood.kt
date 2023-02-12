package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.R
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.core.BaseFragment
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.core.getActivityAboutFavouriteFoodSqlDatabase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.databinding.FragmentFavouriteFoodBinding
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.activity.action_for_activity.interfaces.FoodDb
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.RcvAdapterFavouriteFood
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.interfaces.ClickOnFavouriteFoodItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class FragmentFavouriteFood(): BaseFragment(), ClickOnFavouriteFoodItem {

    private var _binding: FragmentFavouriteFoodBinding? = null
    private val binding get() = _binding!!

    private val rcvAdapterFavouriteFood by lazy {
        RcvAdapterFavouriteFood(this)
    }

    private var activityAboutFavouriteFoodSqlDatabase: FoodDb? = null
    private var isNetworkAvailable: Boolean = false

    private var strNetworkIsUnavailable = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteFoodBinding.inflate(inflater, container, false)

        strNetworkIsUnavailable = resources.getString(R.string.network_is_unavailable)
        initRcvFavouriteFood()

        val networkState = getNetworkStateFlow()
        lifecycleScope.launchWhenStarted {
            networkState.onEach { state ->
                state?.let { stateWithOutNull ->
                    if (stateWithOutNull) {
                        isNetworkAvailable = true
                        val result = activityAboutFavouriteFoodSqlDatabase
                            ?.getFavouriteFoodByCategoryFromLocalDatabase()
                        result?.let {
                            rcvAdapterFavouriteFood.submitNewFoodByCategoryItemList(it)
                        }
                    } else {
                        isNetworkAvailable = false
                    }
                }
            }.collect()
        }

        return binding.root
    }

    private fun initRcvFavouriteFood() {

        binding.rcvFavouriteFood.layoutManager = LinearLayoutManager(
            activity?.applicationContext, RecyclerView.VERTICAL, false)
        binding.rcvFavouriteFood.adapter = rcvAdapterFavouriteFood
        activityAboutFavouriteFoodSqlDatabase = getActivityAboutFavouriteFoodSqlDatabase()

    }

    override fun clickOnFavouriteFoodItem(foodByCategoryItem: FoodByCategoryItem) {

        if (isNetworkAvailable) {
            NavHostFragment.findNavController(this).navigate(
                R.id.action_favourite_food_menu_item_and_nav_host_fragment_item_to_all_info_about_food,
                bundleOf(FragmentAllInformationAboutFood.KEY_FOR_ALL_INFORMATION_FOOD to
                        foodByCategoryItem.idMeal)
            )
        } else {
            showLongToast(strNetworkIsUnavailable)
        }
    }

    override fun clickOnDeleteFavouriteFoodItem(foodByCategoryItem: FoodByCategoryItem) {
        activityAboutFavouriteFoodSqlDatabase?.deleteFavouriteFoodByDatabaseFoodIdFromLocalDatabase(
            foodByCategoryItem.databaseIdFood
        )
        rcvAdapterFavouriteFood.favouriteFoodWasDeleted(foodByCategoryItem.databaseIdFood)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment = FragmentFavouriteFood()
        //:)))) :o
    }

}