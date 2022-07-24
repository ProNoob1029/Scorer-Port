package com.dragos.scorerport.feature_editor.presentation.edit

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dragos.scorerport.feature_editor.presentation.edit.components.Title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen() {
    Scaffold {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Title()
            }
        }
    }
}