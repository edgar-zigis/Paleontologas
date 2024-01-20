package com.zigis.paleontologas.core.entities

import java.util.regex.Pattern
import kotlin.math.max

data class ApplicationVersion(
    val version: String
) : Comparable<ApplicationVersion> {

    override fun compareTo(other: ApplicationVersion): Int {
        val splitVersion = this
            .version
            .split(Pattern.quote(".").toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()

        val splitAnotherVersion = other
            .version
            .split(Pattern.quote(".").toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()

        val count = splitAnotherVersion.size
        val anotherCount = splitAnotherVersion.size
        val maxCount = max(count, anotherCount)

        for (i in 0 until maxCount) {
            val l = Integer.parseInt(splitVersion[i])
            val r = Integer.parseInt(splitAnotherVersion[i])
            if (l > r) {
                return 1
            } else if (l < r) {
                return -1
            }
        }
        return 0
    }
}