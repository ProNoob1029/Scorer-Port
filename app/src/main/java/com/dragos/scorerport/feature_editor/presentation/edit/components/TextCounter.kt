package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.R

@Composable
fun TextCounter (
    modifier: Modifier = Modifier,
    counter: Int,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Counter(
            counter = counter,
            textStyle = textStyle
        )
        Buttons()
    }
}

@Composable
internal fun Counter (
    modifier: Modifier = Modifier,
    counter: Int,
    textStyle: TextStyle
) {
    Text (
        modifier = modifier,
        text = "$counter",
        style = textStyle.copy(fontFeatureSettings = "tnum")
    )
}

@Composable
internal fun Buttons (
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.width(IntrinsicSize.Max)) {
        Button(
            modifier = Modifier
                .weight(1f)
                /*.aspectRatio(1f)*/,
            onClick = { /*TODO*/ },
            contentPadding = PaddingValues(all = 0.dp)
        ) {
            val minus = painterResource(id = R.drawable.ic_baseline_minus_24)
            Icon(minus , contentDescription = "Minus")
        }
        Button(
            modifier = Modifier
                .weight(1f)
                /*.aspectRatio(1f)*/,
            onClick = { /*TODO*/ },
            contentPadding = PaddingValues(all = 0.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
}