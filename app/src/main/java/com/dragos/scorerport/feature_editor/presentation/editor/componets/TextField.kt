package com.dragos.scorerport.feature_editor.presentation.editor.componets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.presentation.editor.EditorViewModel
import com.dragos.scorerport.feature_editor.presentation.util.OutlinedTextField
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField (
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(top = 8.dp, start = 16.dp, end = 16.dp),
    label: String,
    type: MatchEnum.Strings,
    viewModel: EditorViewModel = hiltViewModel(),
) {
    val title by rememberSaveable { viewModel.get(type) }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues),
        value = title,
        onValueChange = { viewModel.set(type, it) },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(autoCorrect = false),
        textStyle = MaterialTheme.typography.titleLarge,
        labelBodySmall = MaterialTheme.typography.titleMedium
    )
}