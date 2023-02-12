package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.data.search_data.local.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.SQLException

class SqlHelper(

    context: Context

): SQLiteOpenHelper(
    context, SqlCommands.SQL_DATABASE_NAME, null, SqlCommands.SQL_DATABASE_VERSION
) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(SqlCommands.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SqlCommands.SQL_DROP_TABLE)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
        onUpgrade(db, oldVersion, newVersion)
    }

}