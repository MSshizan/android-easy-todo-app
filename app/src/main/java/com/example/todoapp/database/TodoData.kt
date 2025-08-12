package com.example.todoapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class TodoData(
    @PrimaryKey(autoGenerate = true)
    val id : Int =0,
    val date: Date,
    val toDo: String,

    )