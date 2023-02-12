package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.usecase.local_database.sql

import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository.DatabaseSql

class OpenCloseSqlDatabaseLocalDataBaseUseCase(

    private val databaseSql: DatabaseSql

) {

    fun onOpenDatabase() {
        databaseSql.onOpenDatabase()
    }

    fun onCloseDatabase() {
        databaseSql.onCloseDatabase()
    }

}