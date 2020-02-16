package com.zigis.paleontologas.quiz.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val periodId: Int,
    val periodName: String,
    val questionIndex: Int,
    val artwork: String,
    var isAnswered: Boolean
) {
    @Ignore
    val variantList: MutableList<String> = mutableListOf()

    fun getCorrectVariantIndex(): Int {
        val correctVariant = variantList.first { it.endsWith("1") }
        return variantList.indexOf(correctVariant)
    }
}