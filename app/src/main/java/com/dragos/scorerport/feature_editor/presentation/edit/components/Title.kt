package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.presentation.util.OutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Title(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TitleCard(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = "New Match",
            titleStyle = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        var title by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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

        Spacer(modifier = Modifier.height(16.dp))

        var activeIndex by rememberSaveable { mutableStateOf( null as Int? ) }

        AllianceButtons(
            modifier = Modifier.padding(horizontal = 16.dp),
            activeIndex = activeIndex,
            onItemClick = { index ->
                activeIndex =
                    if (activeIndex == index)
                        null
                    else index
            }
        )
    }
}