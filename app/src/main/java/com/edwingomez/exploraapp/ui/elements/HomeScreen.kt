package com.edwingomez.exploraapp.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onClickAddTouristicPlace:()->Unit={}) {
    Scaffold(
        topBar = {
            TopAppBar(title = {Text("Explora Colombia") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClickAddTouristicPlace) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) { }
    }
}