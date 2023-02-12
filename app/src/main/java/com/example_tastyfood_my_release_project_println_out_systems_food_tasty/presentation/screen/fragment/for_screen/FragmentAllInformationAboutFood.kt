package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.R
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.core.BaseFragment
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.databinding.FragmentFullInformationAboutFoodByCategoryItemBinding
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.view_model.VmAllInformationAboutFood
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.RcvAdapterFoodById
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentAllInformationAboutFood() : BaseFragment() {

    private var _binding: FragmentFullInformationAboutFoodByCategoryItemBinding? = null
    private val binding get() = _binding!!

    private val viewModelFullInformationAboutFood: VmAllInformationAboutFood
            by viewModel<VmAllInformationAboutFood>()

    private val rcvAdapterFoodById by lazy {
        RcvAdapterFoodById()
    }

    private var strNetworkIsUnavailable = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullInformationAboutFoodByCategoryItemBinding.inflate(
            inflater, container, false
        )

        strNetworkIsUnavailable = resources.getString(R.string.network_is_unavailable)

        initRcAdapterFoodById()

        val networkState = getNetworkStateFlow()
        lifecycleScope.launchWhenStarted {
            networkState.onEach { value ->
                value?.let {
                    if (it) {
                        vmFoodByIdLiveDataObserver()
                        val id = requireArguments().getString(
                            KEY_FOR_ALL_INFORMATION_FOOD
                        )
                        id?.let {
                            viewModelFullInformationAboutFood.getFoodById(id)
                        }
                    }
                }
            }.collect()
        }

        return binding.root
    }

    private fun initRcAdapterFoodById() {
        binding.rcvFoodBYId.layoutManager = LinearLayoutManager(
            this.activity?.applicationContext, RecyclerView.HORIZONTAL,
            false
        )
        binding.rcvFoodBYId.adapter = rcvAdapterFoodById
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun vmFoodByIdLiveDataObserver() {
        viewModelFullInformationAboutFood.foodByIdLiveData.observe(viewLifecycleOwner,
            Observer { foodById ->
                rcvAdapterFoodById.submitNewFoodIdList(foodById.meals)
            }
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment = FragmentAllInformationAboutFood()
        const val KEY_FOR_ALL_INFORMATION_FOOD = "k"
        //;), ;o, ;D
    }

}