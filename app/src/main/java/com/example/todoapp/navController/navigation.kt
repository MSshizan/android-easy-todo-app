package com.example.todoapp.navController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.Comopsoble.Screen
import com.example.todoapp.Comopsoble.TodoContent
import com.example.todoapp.viewModels.TodoViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home", builder = {
        composable("home"){
            val todoViewModel: TodoViewModel =  hiltViewModel()
            Screen(viewModel = todoViewModel, navController = navController )
        }
        composable ("details/{id}"){
            val todoViewModel: TodoViewModel = hiltViewModel()
            val id = it.arguments?.getString("id")
            TodoContent(id = id.toString(), viewModel = todoViewModel)
        }
    })

}
