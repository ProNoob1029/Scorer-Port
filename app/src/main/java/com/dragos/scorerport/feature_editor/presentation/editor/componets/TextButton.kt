package com.dragos.scorerport.feature_editor.presentation.editor.componets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.presentation.editor.EditorViewModel
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum

@Composable
fun TextButton (
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(top = 8.dp, start = 16.dp, end = 16.dp),
    type: MatchEnum.Ints,
    viewModel: EditorViewModel = hiltViewModel()
) {

}