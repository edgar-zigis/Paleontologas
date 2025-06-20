package com.zigis.paleontologas.core.extensions

fun String.sanitized(): String {
    return this
        .trim()
        .lowercase()
        .filter { it.isLetterOrDigit() }
}
