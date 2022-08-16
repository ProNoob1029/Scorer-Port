package com.dragos.scorerport.feature_editor.presentation.editor

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dragos.scorerport.feature_editor.presentation.editor.componets.*
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum

val screen: List<@Composable () -> Unit> = listOf(
    { TextField(label = "Title", type = MatchEnum.Strings.TitleText) },
    { AllianceButtons(firstText = "Red Alliance", secondText = "Blue Alliance", type = MatchEnum.Ints.Alliance) },
    { Title(title = "Autonomous points: ", type = MatchEnum.Ints.AutoTitle) },
    { TextSwitch(text = "Duck delivery: ", type = MatchEnum.Booleans.AutoDuck) },
    { TextCounter(text = "Freight in storage: ", type = MatchEnum.Counters.AutoStorage) }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen (

) {
    Scaffold { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) { items(items = screen) { item -> item() } }
    }
}