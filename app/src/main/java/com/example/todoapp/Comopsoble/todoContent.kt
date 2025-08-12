package com.example.todoapp.Comopsoble


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.todoapp.viewModels.TodoViewModel

@Composable
fun TodoContent(id: String, viewModel: TodoViewModel) {

    val context = LocalContext.current

    val content by viewModel.getContent(id.toInt()).observeAsState(null)

    var text by remember(content) {
        mutableStateOf(content?.content ?: "")
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.SpaceBetween
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().weight(1f),
            value = text,
            onValueChange = {text = it},
            textStyle = TextStyle(
                fontSize = 22.sp
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button({
                viewModel.addContent(todoId = id.toInt(), content = text)
                Toast.makeText(context,"Details Added", Toast.LENGTH_SHORT).show()

            }) {
                Text("Save")
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "Save"
                )

            }
            Button({
                viewModel.DeleteConetent(id = id.toInt())
                Toast.makeText(context,"Details Cleared", Toast.LENGTH_SHORT).show()
            }) {
                Text("Clear")
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear"
                )

            }
        }

    }


}



