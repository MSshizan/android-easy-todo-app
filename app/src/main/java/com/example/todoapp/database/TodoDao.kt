package com.example.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TodoDao {

    @Query("SELECT * FROM  TodoData")
    fun getAllTodo() : LiveData<List<TodoData>>

    @Query("SELECT * FROM TodoContentData WHERE todoId = :todoId LIMIT 1")
    suspend fun getContentSync(todoId: Int): TodoContentData?

    @Query("SELECT * FROM TodoContentData WHERE todoId = :todoId LIMIT 1")
   fun getContentByTodoId(todoId: Int): LiveData<TodoContentData>

    @Insert
    suspend fun addToDo(todoData: TodoData)

    @Insert
    suspend fun addContent(content: TodoContentData)

    @Update
    suspend fun updateContent(content: TodoContentData)


    @Query("Delete  FROM TodoData WHERE id = :id")
    suspend fun deleteTodo(id: Int)

    @Query("Delete FROM TodoContentData WHERE todoId = :id")
    suspend fun deleteContent(id : Int)

}