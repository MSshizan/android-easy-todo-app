package com.example.todoapp.database

import androidx.room.TypeConverter
import java.util.Date


class Convertor {

    @TypeConverter
    fun fromDate(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun toLong(time : Long) : Date{
        return Date(time)
    }
}