package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.core

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.R
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.network.ConnectivityObserver
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.network.NetworkConnectivityObserver
import kotlinx.coroutines.flow.*

open class BaseFragment() : Fragment() {

    private lateinit var connectivityObserver: ConnectivityObserver
    private var mutableIsNetworkAvailableStateFlow = MutableStateFlow<Boolean?>(
        value = null
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(requireContext())
        connectivityObserver.observe().onEach {
            when (it) {
                ConnectivityObserver.Status.Available -> {
                    mutableIsNetworkAvailableStateFlow.value = true
                }

                ConnectivityObserver.Status.Losing -> {
                    mutableIsNetworkAvailableStateFlow.value = false
                }

                ConnectivityObserver.Status.Unavailable -> {
                    mutableIsNetworkAvailableStateFlow.value = false
                }

                ConnectivityObserver.Status.Lost -> {
                    mutableIsNetworkAvailableStateFlow.value = false
                    showLongToast(
                        "Network is unavailable, try to connect " +
                                "to the new network!"
                    )
                }

            }
        }.launchIn(lifecycleScope)
    }

    fun getNetworkStateFlow(): StateFlow<Boolean?> {
        return mutableIsNetworkAvailableStateFlow.asStateFlow()
    }

    fun showLongToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

}