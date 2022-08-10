package com.dragos.scorerport.feature_editor.presentation.edit

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.presentation.edit.components.*
import com.dragos.scorerport.feature_editor.presentation.util.OutlinedTextField
import com.dragos.scorerport.ui.theme.padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    viewModel: EditViewModel = hiltViewModel(),
) {
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*item {
                TitleCard(
                    title = "New Match",
                    titleStyle = MaterialTheme.typography.headlineLarge
                )

                var title by rememberSaveable { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.padding.inRow),
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
                    },
                    redText= "Red Alliance",
                    blueText= "Blue Alliance",
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

                var counter by rememberSaveable { mutableStateOf(0) }

                TextCounter(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    counter = counter,
                    text = "Freight in storage",
                    onClickPlus = { counter++ },
                    onClickMinus = { counter-- },
                    plusEnabled = counter < 10,
                    minusEnabled = counter > 0
                )
            }*/

            items(
                items = viewModel.screen.elements
            ) { item ->
                when(item) {
                    is Title -> {
                        TitleCard(
                            title = item.title,
                            points = if (item.counter) 0 else null,
                            titleStyle = if (item.largeTitle) MaterialTheme.typography.headlineLarge else null
                        )
                    }
                    is TextField -> {
                        val value by viewModel.state.getString(item.type)
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(MaterialTheme.padding.inRow),
                            value = value,
                            onValueChange = { viewModel.state.setString(item.type, it) },
                            label = {
                                Text(
                                    text = item.label,
                                )
                            },
                            keyboardOptions = KeyboardOptions(autoCorrect = false),
                            textStyle = MaterialTheme.typography.titleLarge,
                            labelBodySmall = MaterialTheme.typography.titleMedium,
                        )
                    }
                    is AllianceButtons -> {
                        val activeIndex by viewModel.state.getInt(item.type)
                        AllianceButtons(
                            modifier = Modifier
                                .padding(all = 16.dp),
                            activeIndex = activeIndex,
                            onItemClick = { index ->
                                viewModel.state.setInt(
                                    item.type,
                                    value = if (activeIndex == index)
                                        0
                                    else index
                                )
                            },
                            redText= item.text1,
                            blueText= item.text2,
                        )
                    }
                    is Switch -> {
                        val checked by viewModel.state.getBoolean(item.type)

                        val visible by viewModel.state.getVisibility(item.type)

                        AnimatedVisibility(
                            visible = visible,
                            enter = fadeIn() + slideInVertically(),
                            exit = fadeOut() + slideOutVertically()
                        ) {
                            TextSwitch(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 8.dp),
                                checked = checked,
                                onCheckedChange = { viewModel.state.setBoolean(item.type, it) },
                                text = item.label
                            )
                        }
                    }
                    is Counter -> {
                        val counter by viewModel.state.getInt(item.type)
                        TextCounter(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 8.dp),
                            counter = counter,
                            text = item.label,
                            onClickPlus = { viewModel.state.setInt(item.type, counter + 1) },
                            onClickMinus = { viewModel.state.setInt(item.type, counter - 1) },
                            plusEnabled = counter < 10,
                            minusEnabled = counter > 0
                        )
                    }
                    is SegmentedButton -> {
                        val selectedIndex by viewModel.state.getInt(item.type)
                        TextButtons(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 8.dp),
                            items = item.buttons,
                            label = item.label,
                            selectedIndex = selectedIndex,
                            onItemClick = { index ->
                                viewModel.state.setInt(
                                    item.type,
                                    value = if (index + 1 == selectedIndex) 0 else index + 1
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}