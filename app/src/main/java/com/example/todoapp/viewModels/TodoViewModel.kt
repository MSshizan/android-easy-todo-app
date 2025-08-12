package com.example.todoapp.viewModels

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.database.TodoContentData
import com.example.todoapp.database.TodoDao
import com.example.todoapp.database.TodoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class TodoViewModel @Inject constructor(
    private val toDoDao: TodoDao

): ViewModel() {

    private val _todoItems : LiveData<List<TodoData>> = toDoDao.getAllTodo()
    val todoItems: LiveData<List<TodoData>> get() = _todoItems



    fun add(todo:String){
        viewModelScope.launch{
            if(todo.isNotBlank()){
                toDoDao.addToDo(todoData = TodoData(toDo = todo, date = Date()))
            }else{
                return@launch
            }

        }

    }

    fun Delete(id:Int){
        viewModelScope.launch() {
            toDoDao.deleteTodo(id)
            toDoDao.deleteContent(id)
        }


    }

    fun DeleteConetent(id:Int){
        viewModelScope.launch() {
            toDoDao.deleteContent(id)

        }


    }

    fun addContent(todoId : Int , content: String){
        viewModelScope.launch ( ){
            val exsitingContent = toDoDao.getContentSync(todoId)

            if (exsitingContent != null){
                val updated = exsitingContent.copy(content = content)
                toDoDao.updateContent(updated)
            }
            else{
                toDoDao.addContent(content = TodoContentData(content = content, todoId = todoId,))
            }

        }


    }





    fun getContent(todid : Int): LiveData<TodoContentData>{
        return toDoDao.getContentByTodoId(todid)

    }




}