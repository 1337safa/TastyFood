package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.activity.for_screan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.R
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.databinding.ActivityMainBinding
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.local_database.sql.FavouriteFoodByCategoryInLocalDataBaseUseCase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.activity.action_for_activity.interfaces.FoodDb
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.activity.view_model.VmMainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity(): AppCompatActivity(), FoodDb {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val vmMainActivity: VmMainActivity by viewModel<VmMainActivity>()
    private val favouriteFoodByCategoryUseCase: FavouriteFoodByCategoryInLocalDataBaseUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vmMainActivity.onOpenDatabase()
        initNavHost()

    }

    private fun BottomNavigationView.setSetupWithNavController(navController: NavController?) {

        navController?.let {
            setupWithNavController(it)
        }

        setOnItemSelectedListener { menuItem ->

            val builder = NavOptions.Builder().setLaunchSingleTop(true).setRestoreState(false)
            val graph = navController?.currentDestination?.parent
            val destination = graph?.findNode(menuItem.itemId)
            if (menuItem.order and Menu.CATEGORY_SECONDARY == 0) {
                navController?.graph?.findStartDestination()?.id?.let {
                    builder.setPopUpTo(
                        it,
                        inclusive = false,
                        saveState = true
                    )
                }
            }

            val options = builder.build()
            destination?.id?.let { id ->
                navController.navigate(id, null, options)
            }
            return@setOnItemSelectedListener true
        }

    }

    private fun initNavHost() {

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fcv_main_activity_nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainActivityBottomNavigationView.setSetupWithNavController(navController)

    }

    override fun insertFoodByCategoryToLocalDatabase(foodByCategoryItem: FoodByCategoryItem) {

        Toast.makeText(this, resources.getString(R.string.saved),
        Toast.LENGTH_SHORT).show()
        vmMainActivity.insertFoodByCategory(foodByCategoryItem)

    }
    
    override fun getFavouriteFoodByCategoryFromLocalDatabase(): ArrayList<FoodByCategoryItem>? {
        return favouriteFoodByCategoryUseCase.getFavouriteFoodByCategory()
    }

    override fun deleteFavouriteFoodByDatabaseFoodIdFromLocalDatabase(databaseIdFood: String?) {

        Toast.makeText(this, resources.getString(R.string.deleted),
        Toast.LENGTH_SHORT).show()
        vmMainActivity.deleteFavouriteFoodByDatabaseFoodId(databaseIdFood)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /* @Made by 1337safa */
}