package com.dragos.scorerport.feature_editor.presentation.util

import java.security.SecureRandom
import java.util.*

private const val AUTO_ID_LENGTH = 20

private const val AUTO_ID_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

private val rand: Random = SecureRandom()

fun autoId(): String {
    val builder = StringBuilder()
    val maxRandom = AUTO_ID_ALPHABET.length
    for (i in 0 until AUTO_ID_LENGTH) {
        builder.append(AUTO_ID_ALPHABET[rand.nextInt(maxRandom)])
    }
    return builder.toString()
}