package com.example.todoapp.database
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object appmoulde {

    @Provides
    @Singleton
    fun Providers(@ApplicationContext context: Context) :TodoDateBase{
        return Room.databaseBuilder(
            context,
            TodoDateBase::class.java,
            "Todo_DataBase",

        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun todoDao(database: TodoDateBase): TodoDao{
        return database.getAllTodo()
    }

}