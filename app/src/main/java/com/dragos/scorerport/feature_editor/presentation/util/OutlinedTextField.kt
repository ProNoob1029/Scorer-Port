package com.dragos.scorerport.feature_editor.presentation.util

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.lerp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit),
    labelBodySmall: TextStyle,
    labelBodyLarge: TextStyle = textStyle,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(4.0.dp),
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    @OptIn(ExperimentalMaterial3Api::class)
    (BasicTextField(
        value = value,
        modifier = modifier
            .padding(top = 8.dp)
            .background(colors.containerColor(enabled).value, shape)
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = TextFieldDefaults.MinHeight
            ),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = placeholder,
                label = {
                    MyLabel(
                        value = value,
                        label = label,
                        interactionSource = interactionSource,
                        bodySmall = labelBodySmall,
                        bodyLarge = labelBodyLarge,
                        visualTransformation = visualTransformation
                    ) },
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                border = {
                    TextFieldDefaults.BorderBox(
                        enabled,
                        isError,
                        interactionSource,
                        colors,
                        shape
                    )
                }
            )
        }
    ))
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyLabel (
    visualTransformation: VisualTransformation = VisualTransformation.None,
    value: String,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    label: @Composable (() -> Unit),
    bodySmall: TextStyle = MaterialTheme.typography.bodySmall,
    bodyLarge: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    val transformedText = remember(value, visualTransformation) {
        visualTransformation.filter(AnnotatedString(value))
    }.text.text

    val isFocused = interactionSource.collectIsFocusedAsState().value

    val inputState = when {
        isFocused -> InputPhase.Focused
        transformedText.isEmpty() -> InputPhase.UnfocusedEmpty
        else -> InputPhase.UnfocusedNotEmpty
    }

    val transition = updateTransition(inputState, label = "TextFieldInputState")

    val labelProgress by transition.animateFloat(
        label = "LabelProgress",
        transitionSpec = { tween(durationMillis = AnimationDuration) }
    ) {
        when (it) {
            InputPhase.Focused -> 1f
            InputPhase.UnfocusedEmpty -> 0f
            InputPhase.UnfocusedNotEmpty -> 1f
        }
    }

    val labelTextStyle = lerp(
        bodyLarge,
        bodySmall,
        labelProgress
    )

    ProvideTextStyle(
        value = labelTextStyle,
        content = label
    )
}

private enum class InputPhase {
    // Text field is focused
    Focused,

    // Text field is not focused and input text is empty
    UnfocusedEmpty,

    // Text field is not focused but input text is not empty
    UnfocusedNotEmpty
}

internal const val AnimationDuration = 150 //150