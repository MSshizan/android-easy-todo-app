package com.example.todoapp.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters



@Database(entities = [TodoData::class, TodoContentData::class], version = 2, exportSchema = false)
@TypeConverters(Convertor::class)
abstract class TodoDateBase : RoomDatabase(){

    companion object{
        const val name = "Todo_DataBase"
    }
    abstract fun getAllTodo (): TodoDao


}