package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen

import android.content.Context
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.R
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.core.*
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.databinding.FragmentMainContentBinding
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.databinding.SearchFoodByNameAlertViewBinding
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_name.FoodByNameItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_category.AllFoodCategoriesItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.network.okhttp.FoodByNameFromNetworkUseCase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.activity.action_for_activity.interfaces.FavouriteFoodDb
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.alert.RcvAdapterSearchFoodByNameAlertDialog
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.alert.interfaces.ClickOnSomeFoodByName
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.view_model.VmMainContent
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.RcvAdapterFoodByCategory
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.RcvAdapterAllFoodCategories
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.interfaces.ClickOnFoodByCategoryItemAdapter
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.interfaces.ClickOnFoodCategory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

const val DEFAULT_FOOD_CATEGORY = "Pasta"
const val KEY_FOR_SAVE_FOOD_CATEGORY = "ThisIsKeyForFoodCategory"

class FragmentMainContent(): BaseFragment(), ClickOnFoodByCategoryItemAdapter, ClickOnFoodCategory,
    ClickOnSomeFoodByName {
    /* :o */
    private var _binding: FragmentMainContentBinding? = null
    private val binding get() = _binding!!

    private var _searchFoodByNameAlertViewBinding: SearchFoodByNameAlertViewBinding? = null
    private val searchFoodByNameAlertViewBinding get() = _searchFoodByNameAlertViewBinding!!

    private val viewModelMainContent: VmMainContent by viewModel()
    private val foodByNameFromNetworkUseCase: FoodByNameFromNetworkUseCase by inject<FoodByNameFromNetworkUseCase>()

    private var currentFoodCategory: String = DEFAULT_FOOD_CATEGORY

    private val foodCategoryRcvAdapter by lazy {
        RcvAdapterAllFoodCategories(this)
    }

    private val foodByCategoryRcvAdapter by lazy {
        RcvAdapterFoodByCategory(this)
    }

    private val rcvAdapterSearchFoodByNameAlertDialog by lazy {
        RcvAdapterSearchFoodByNameAlertDialog(this)
    }

    private var alertDialogForShowFoodByIdOrNull: AlertDialog? = null
    private var activityAboutFavouriteFoodSqlDatabase: FavouriteFoodDb? = null

    private var isNetworkAvailable: Boolean = false

    private var strNetworkIsUnavailable = ""
    private var strFoodNotFound = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainContentBinding.inflate(layoutInflater, container, false)
        _searchFoodByNameAlertViewBinding = SearchFoodByNameAlertViewBinding.inflate(
            layoutInflater, container, false
        )

        strNetworkIsUnavailable = resources.getString(R.string.network_is_unavailable)
        strFoodNotFound = resources.getString(R.string.food_not_found)

        activityAboutFavouriteFoodSqlDatabase = getActivityAboutFavouriteFoodSqlDatabase()

        val savedCategory = savedInstanceState?.getString(KEY_FOR_SAVE_FOOD_CATEGORY)
        savedCategory?.let { this.currentFoodCategory = it }
        val networkState = getNetworkStateFlow()
        listenToNetworkStatusAndShowDataAndReportNetworkState(networkState)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edtvUserFoodListener()
    }

    private fun init() {
        initRcvFoodCategoryView()
        initRcvFoodByCategoryView()
        viewModelMainContent.getAllFoodCategories()
        viewModelMainContent.getAllFoodByCategory(currentFoodCategory)
        viewModelMainContentFoodCategoryLiveDataListener()
        viewModelMainContentFoodByCategoryLiveDataListener()
    }

    private fun initRcvFoodCategoryView() {
        binding.rcvFoodCategory.layoutManager = LinearLayoutManager(
            this.activity?.applicationContext, RecyclerView.HORIZONTAL,
            false
        )
        binding.rcvFoodCategory.adapter = foodCategoryRcvAdapter
    }

    private fun initRcvFoodByCategoryView() {
        binding.rcvFoodByCategory.layoutManager = LinearLayoutManager(
            this.activity?.applicationContext, RecyclerView.HORIZONTAL,
            false
        )
        binding.rcvFoodByCategory.adapter = foodByCategoryRcvAdapter
    }

    private fun listenToNetworkStatusAndShowDataAndReportNetworkState(
        networkState: StateFlow<Boolean?>
    ) {
        lifecycleScope.launchWhenStarted {

            networkState.onEach { isNetwork ->
                isNetwork?.let { networkWithOutNull ->
                    if (networkWithOutNull) {
                        isNetworkAvailable = true
                        init()
                    } else {
                        isNetworkAvailable = false
                    }
                }
            }.collect()

        }
    }

    private fun edtvUserFoodListener() {
        binding.edtvUserFood.setOnEditorActionListener(object: TextView.OnEditorActionListener {
                override fun onEditorAction(textView: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                    var handled = false
                    if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                        val foodName = textView?.text.toString()
                        try {
                            if (isNetworkAvailable) {
                                lifecycleScope.launch(Dispatchers.IO) {
                                    val foodByNameItems = foodByNameFromNetworkUseCase.getFoodByNameOrNull(
                                        foodName
                                    )
                                    if (foodByNameItems != null) {
                                        withContext(Dispatchers.Main) {
                                            showAlertDialogSearchFoodByNameAlertViewByResult(
                                                foodByNameItems
                                            )
                                        }
                                    } else {
                                        withContext(Dispatchers.Main) {
                                            showLongToast(strFoodNotFound)
                                        }
                                    }
                                }
                            } else {
                                showLongToast(strNetworkIsUnavailable)
                            }
                        } catch (e: Exception) {
                            throw e
                        }
                        handled = true
                        hideInputMethod()
                    }
                    return handled
                }
            }
        )
    }

    private fun getAlertDialogWithOnlyViewOrNull(view: View, context: Context): AlertDialog {

        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        return builder.create()

    }

    private fun showAlertDialogSearchFoodByNameAlertViewByResult(
        resultFromServer: List<FoodByNameItem>
    ) {
        searchFoodByNameAlertViewBinding?.rcvFoodByName?.adapter =
            rcvAdapterSearchFoodByNameAlertDialog
        searchFoodByNameAlertViewBinding?.rcvFoodByName?.layoutManager = LinearLayoutManager(
            context, RecyclerView.HORIZONTAL, false
        )
        rcvAdapterSearchFoodByNameAlertDialog.submitNewFoodByNameList(
            resultFromServer
        )

        searchFoodByNameAlertViewBinding?.let {
            alertDialogForShowFoodByIdOrNull = getAlertDialogWithOnlyViewOrNull(
                it.root, requireContext()
            )
            alertDialogForShowFoodByIdOrNull?.setCancelable(false)
            alertDialogForShowFoodByIdOrNull?.show()
        }

    }

    private fun hideInputMethod() {
        val imm = activity?.applicationContext?.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun viewModelMainContentFoodByCategoryLiveDataListener() {
        viewModelMainContent.foodByCategoryLiveData.observe(
            this@FragmentMainContent.viewLifecycleOwner,
            Observer {
                this@FragmentMainContent.foodByCategoryRcvAdapter
                    .submitNewFoodByCategoryList(it.meals)
            })
    }

    private fun viewModelMainContentFoodCategoryLiveDataListener() {
        viewModelMainContent.foodCategoryLiveData.observe(viewLifecycleOwner,
            Observer {
                this.foodCategoryRcvAdapter.submitNewFoodCategoryList(it.categories)
            })
    }

    override fun clickOnFoodByCategoryItemFromRcvAdapterFoodByCategory(
        foodByCategoryItem: FoodByCategoryItem
    ) {

        if (isNetworkAvailable) {

            navigateToFragmentByAction(R.id.action_main_content_nav_host_item_to_all_information_food,
                bundleOf(FragmentAllInformationAboutFood.KEY_FOR_ALL_INFORMATION_FOOD
                            to foodByCategoryItem.idMeal), this)

        } else {
            showLongToast(strNetworkIsUnavailable)
        }

    }

    override fun clickOnBtnFavouriteFoodFromRcvAdapterFoodByCategory(
        foodByCategoryItem: FoodByCategoryItem
    ) {
        activityAboutFavouriteFoodSqlDatabase?.insertFoodByCategoryToLocalDatabase(
            foodByCategoryItem
        )
    }

    override fun clickOnFoodCategoryFromRcvAdapterFoodCategory(
        allFoodCategoriesItem: AllFoodCategoriesItem
    ) {

        if (this.currentFoodCategory != allFoodCategoriesItem.strCategory) {
            if (isNetworkAvailable) {
                this.currentFoodCategory = allFoodCategoriesItem.strCategory
                this.viewModelMainContent.getAllFoodByCategory(
                    allFoodCategoriesItem.strCategory
                )
            } else {
                showLongToast(strNetworkIsUnavailable)
            }
        }

    }

    override fun clickOnMoreFromRcvAdapterSearchFoodByNameAlertView(
        foodByNameItem: FoodByNameItem
    ) {

        if (isNetworkAvailable) {
            alertDialogForShowFoodByIdOrNull.let {
                it?.dismiss()
            }
            navigateToFragmentByAction(R.id.all_information_about_food,
                bundleOf(FragmentAllInformationAboutFood.KEY_FOR_ALL_INFORMATION_FOOD
                            to foodByNameItem.idMeal), this)
        } else {
            showLongToast(strNetworkIsUnavailable)
        }

    }

    override fun clickOnCancelFromRcvAdapterSearchFoodByNameAlertView() {
        alertDialogForShowFoodByIdOrNull?.let {
            it.dismiss()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_FOR_SAVE_FOOD_CATEGORY, this.currentFoodCategory)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _searchFoodByNameAlertViewBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment = FragmentMainContent()
        //;), ;o, ;D
    }

}