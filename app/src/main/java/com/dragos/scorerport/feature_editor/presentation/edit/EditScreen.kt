package com.dragos.scorerport.feature_editor.presentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.presentation.edit.components.Title
import com.dragos.scorerport.feature_editor.presentation.util.SegmentedButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen() {
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Title()
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Parked: ",
                        style = MaterialTheme.typography.titleLarge
                    )

                    val defaultValue: Int? = null
                    var selectedIndex by remember{ mutableStateOf(defaultValue) }
                    SegmentedButton(
                        items = listOf(
                            "Not Parked",
                            "Storage",
                            "Warehouse"
                        ),
                        selectedIndex = selectedIndex,
                        onItemClick = { index ->
                            selectedIndex = if(index == selectedIndex)
                                null
                            else
                                index
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}