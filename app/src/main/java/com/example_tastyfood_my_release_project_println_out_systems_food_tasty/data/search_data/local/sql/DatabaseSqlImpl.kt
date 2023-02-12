package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.local.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.repository.DatabaseSql

class DatabaseSqlImpl(

    context: Context

): DatabaseSql {

    private val sqlHelper: SqlHelper = SqlHelper(context)
    private var db: SQLiteDatabase? = null

    override fun onOpenDatabase() {
        db = sqlHelper.writableDatabase
    }

    override fun insertFoodByCategory(foodByCategoryItem: FoodByCategoryItem) {
        val contentValues = ContentValues()
        contentValues.put(SqlCommands.SQL_COLUMN_NAME_FOOD_IDENTIFICATION, foodByCategoryItem.idMeal)
        contentValues.put(SqlCommands.SQL_COLUMN_NAME_STR_FOOD, foodByCategoryItem.strMeal)
        contentValues.put(SqlCommands.SQL_COLUMN_NAME_STR_FOOD_THUMB, foodByCategoryItem.strMealThumb)
        db?.insert(SqlCommands.SQL_TABLE_NAME, null, contentValues)
    }

    override fun getFavouriteFoodByCategory(): ArrayList<FoodByCategoryItem>? {
        val results = ArrayList<FoodByCategoryItem>()

        val cursor = db?.query(
            SqlCommands.SQL_TABLE_NAME, null, null, null,
            null, null, null
        )

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {

                    val idMeal = cursor.getString(
                        cursor.getColumnIndexOrThrow(SqlCommands.SQL_COLUMN_NAME_FOOD_IDENTIFICATION)
                    )
                    val strFood = cursor.getString(
                        cursor.getColumnIndexOrThrow(SqlCommands.SQL_COLUMN_NAME_STR_FOOD)
                    )
                    val strThumb = cursor.getString(
                        cursor.getColumnIndexOrThrow(SqlCommands.SQL_COLUMN_NAME_STR_FOOD_THUMB)
                    )
                    val databaseIdFood = cursor.getString(
                        cursor.getColumnIndexOrThrow(SqlCommands.SQL_COLUMN_NAME_IDENTIFICATION)
                    )
                    val item = FoodByCategoryItem(
                        idMeal, strFood, strThumb, databaseIdFood
                    )

                    results.add(item)
                }

            } catch (e: Exception) {
                throw e
            }
        }

        cursor?.close()
        return results
    }

    override fun deleteFavouriteFoodByDatabaseFoodId(databaseIdFood: String?) {
        db?.delete(SqlCommands.SQL_TABLE_NAME,
            SqlCommands.SQL_COLUMN_NAME_IDENTIFICATION + "=?", arrayOf(databaseIdFood)
        )
    }

    override fun onCloseDatabase() {
        db?.close()
    }

}