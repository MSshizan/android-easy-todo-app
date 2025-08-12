package com.example.todoapp.Comopsoble


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
 fun AppContent(content :@Composable ()-> Unit) {

    val padding = WindowInsets.statusBars.union(WindowInsets.navigationBars).asPaddingValues()

    Column(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        content()
    }

}