package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.local.sql

object SqlCommands {
    /* ;O */
    const val SQL_DATABASE_NAME = "favourite_food_sql_database.db"
    const val SQL_DATABASE_VERSION = 1

    const val SQL_TABLE_NAME = "favourite_food_table"
    const val SQL_COLUMN_NAME_FOOD_IDENTIFICATION = "idFood"
    const val SQL_COLUMN_NAME_STR_FOOD = "strFood"
    const val SQL_COLUMN_NAME_STR_FOOD_THUMB = "strFoodThumb"
    const val SQL_COLUMN_NAME_IDENTIFICATION = "_id"

    const val SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $SQL_TABLE_NAME (" +
            "$SQL_COLUMN_NAME_IDENTIFICATION INTEGER PRIMARY KEY," +
            "$SQL_COLUMN_NAME_FOOD_IDENTIFICATION TEXT," +
            "$SQL_COLUMN_NAME_STR_FOOD TEXT," +
            "$SQL_COLUMN_NAME_STR_FOOD_THUMB TEXT" +
            ")"
    const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $SQL_TABLE_NAME"

}