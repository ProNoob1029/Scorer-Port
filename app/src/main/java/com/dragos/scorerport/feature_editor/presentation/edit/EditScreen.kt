package com.dragos.scorerport.feature_editor.presentation.edit

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.presentation.edit.components.TextButtons
import com.dragos.scorerport.feature_editor.presentation.edit.components.Title
import com.dragos.scorerport.feature_editor.presentation.edit.components.TitleCard

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
                Title(modifier = Modifier.padding(bottom = 16.dp))

                TitleCard(
                    title = "Autonomous: ",
                    points = 100,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                var selectedIndex by rememberSaveable { mutableStateOf(null as Int?) }

                val view = LocalView.current

                TextButtons(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    items = listOf(
                        "Storage",
                        "Warehouseeeeeeee"
                    ),
                    label = "Parked in: ",
                    selectedIndex = selectedIndex,
                    onItemClick = { index ->
                        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
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