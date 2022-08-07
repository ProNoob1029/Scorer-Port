package com.dragos.scorerport.feature_editor.presentation.edit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.presentation.edit.components.*
import com.dragos.scorerport.feature_editor.presentation.util.OutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen() {
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TitleCard(
                    title = "New Match",
                    titleStyle = MaterialTheme.typography.headlineLarge
                )

                var title by rememberSaveable { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    value = title,
                    onValueChange = { title = it },
                    label = {
                        Text(
                            text = "Title",
                        )
                    },
                    keyboardOptions = KeyboardOptions(autoCorrect = false),
                    textStyle = MaterialTheme.typography.titleLarge,
                    labelBodySmall = MaterialTheme.typography.titleMedium,
                )

                var activeIndex by rememberSaveable { mutableStateOf( null as Int? ) }

                AllianceButtons(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    activeIndex = activeIndex,
                    onItemClick = { index ->
                        activeIndex =
                            if (activeIndex == index)
                                null
                            else index
                    }
                )

                TitleCard(
                    title = "Autonomous: ",
                    points = 100,
                    modifier = Modifier.padding(top = 16.dp)
                )

                var selectedIndex by rememberSaveable { mutableStateOf(null as Int?) }

                TextButtons(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    items = listOf("Storage", "Warehouse"),
                    label = "Parked in: ",
                    selectedIndex = selectedIndex,
                    onItemClick = { index ->
                        selectedIndex = if(index == selectedIndex)
                            null
                        else
                            index
                    },
                )

                var checked by rememberSaveable { mutableStateOf(false) }

                TextSwitch(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    checked = checked,
                    onCheckedChange = { checked = it },
                    text = "Duck delivery"
                )

                TextCounter(
                    modifier = Modifier.padding(top = 8.dp),
                    counter = 20,
                    text = "eh"
                )
            }
        }
    }
}