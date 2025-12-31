package com.zigis.paleontologas.features.quiz.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val periodId: Int,
    val periodName: String,
    val questionIndex: Int,
    val artwork: String,
    val category: String,
    var isAnswered: Boolean
) {
    enum class Category(val value: String) {
        PRECAMBRIAN("precambrian"),
        PALEOZOIC("paleozoic"),
        MESOZOIC("mesozoic"),
        CENOZOIC("cenozoic"),
        FOSSIL("fossil");

        companion object {
            fun fromString(value: String): Category =
                entries.find { it.value == value } ?: PRECAMBRIAN
        }
    }

    @Ignore
    val variantList: MutableList<String> = mutableListOf()

    fun getCorrectVariantIndex(): Int {
        val correctVariant = variantList.first { it.endsWith("1") }
        return variantList.indexOf(correctVariant)
    }

    fun getCategory(): Category {
        return Category.fromString(category)
    }
}