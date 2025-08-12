package com.example.todoapp.Comopsoble

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.database.TodoData
import com.example.todoapp.viewModels.TodoViewModel
import java.text.SimpleDateFormat
import java.util.Locale



@Composable
fun Screen(viewModel: TodoViewModel, navController: NavController) {

    val todolist by viewModel.todoItems.observeAsState(emptyList())
   Column (
       modifier = Modifier
           .fillMaxSize()
           .fillMaxSize()
           .padding(8.dp)
   ){

       AddTodo(viewModel)



       if(todolist.isEmpty()){
           Text(
               modifier = Modifier.fillMaxWidth(),
               text = "Nothing To Do",
               textAlign = TextAlign.Center

           )

       }else{
           LazyColumn(content = {
               itemsIndexed(todolist, itemContent = {
                       index,item ->
                   TodoItem(item, onDelete = {
                       viewModel.Delete(item.id)
                   },
                       onclick = {
                           navController.navigate("details/${item.id}")

                       }
                   )
               })
           } )
       }

   }

}


@Composable
fun AddTodo(viewModel: TodoViewModel) {
    var Todo by remember { mutableStateOf("") }
    val context = LocalContext.current
    var iserror by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = Todo,
            onValueChange = {
                if(it.length <= 30){
                    Todo= it
                    iserror = false
                }


            },
            label = { Text("Enter Todo") },
            singleLine = true,
            isError = iserror
        )

        Button({
            if(Todo.isNotBlank()){
                viewModel.add(Todo)
                Toast.makeText(context,"$Todo Added",Toast.LENGTH_SHORT).show()
                Todo =" "

            }else{
                Toast.makeText(context,"Give a Title For To Do",Toast.LENGTH_SHORT).show()
                iserror = true
            }





        }) {
            Text("ADD")
        }
    }
    
}

@Composable
fun TodoItem(item: TodoData, onDelete : ()-> Unit, onclick : ()-> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.LightGray)
            .height(90.dp)
            .padding(16.dp)
            .clickable(
                onClick = onclick
            )


    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.toDo,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(item.date)
            )

        }

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete"

                )
        }

    }
    
}



