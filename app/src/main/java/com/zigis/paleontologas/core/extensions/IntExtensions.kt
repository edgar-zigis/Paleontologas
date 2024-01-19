package com.zigis.paleontologas.core.extensions

fun Int.withAlpha(alpha: Int): Int {
    require(alpha in 0..0xFF)
    return this and 0x00FFFFFF or (alpha shl 24)
}