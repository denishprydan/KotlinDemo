package com.e.prydantest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.e.prydantest.model.LoginModel

@Dao
public interface DaoAccess {

    @Insert
    fun insertTodo(todo: LoginModel?): Long

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_LOGIN)
    fun fetchAllLogin(): List<LoginModel?>?
}