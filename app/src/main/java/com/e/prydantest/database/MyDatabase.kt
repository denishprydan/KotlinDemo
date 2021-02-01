package com.e.prydantest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.prydantest.model.LoginModel


@Database(entities = [LoginModel::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun daoAccess(): DaoAccess?

    companion object {
        const val DB_NAME = "app_db"
        const val TABLE_NAME_LOGIN = "login"
    }
}
