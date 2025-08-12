package com.example.todoapp.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class TodoContentData(
    @PrimaryKey(autoGenerate = true) val id : Int =0,
    val content:String,
    val todoId : Int
)