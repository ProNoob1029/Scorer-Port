package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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
            var text1 by rememberSaveable { mutableStateOf("") }
            //var text2 by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text1,
                onValueChange = { text1 = it },
                placeholder = {
                    Text(
                        text = "Title",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                textStyle = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            AllianceButtons()
        }
    }
}