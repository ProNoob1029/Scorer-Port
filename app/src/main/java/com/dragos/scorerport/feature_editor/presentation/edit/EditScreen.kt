package com.dragos.scorerport.feature_editor.presentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.presentation.edit.components.Title
import com.dragos.scorerport.feature_editor.presentation.edit.components.TitleCard
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

                Spacer(modifier = Modifier.height(16.dp))

                TitleCard(title = "Autonomous: ", points = 100, modifier = Modifier.padding(horizontal = 16.dp))

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Parked: ",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(1f),
                    )

                    var selectedIndex by rememberSaveable { mutableStateOf(null as Int?) }
                    SegmentedButton(
                        items = listOf(
                            "No",
                            "Partly",
                            "Full"
                        ),
                        selectedIndex = selectedIndex,
                        onItemClick = { index ->
                            selectedIndex = if(index == selectedIndex)
                                null
                            else
                                index
                        },
                    )
                }
            }
        }
    }
}