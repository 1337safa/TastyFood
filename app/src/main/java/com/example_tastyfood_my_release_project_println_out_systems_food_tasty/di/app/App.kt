package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.di.app

import android.app.Application
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.di.appModule
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.di.dataModule
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App(): Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }

    }

}