package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.presentation.util.OutlinedTextField

@Composable
fun Title(
    modifier: Modifier = Modifier,
    surfaceColor: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            color = surfaceColor,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "New Match",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            var title by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                label = {
                    Text(
                        text = "Title",
                    )
                },
                textStyle = MaterialTheme.typography.titleLarge,
                keyboardOptions = KeyboardOptions(autoCorrect = false),
                labelBodySmall = MaterialTheme.typography.titleSmall,
            )

            Spacer(modifier = Modifier.height(16.dp))

            AllianceButtons()
        }
    }
}