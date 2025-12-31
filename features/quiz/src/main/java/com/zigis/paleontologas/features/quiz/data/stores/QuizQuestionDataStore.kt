package com.zigis.paleontologas.features.quiz.data.stores

import com.zigis.paleontologas.features.quiz.data.Question

interface QuizQuestionDataStoreProtocol {
    suspend fun getQuestionDefinitions(periodId: Int): List<QuizQuestionDataStore.DTO>
}

class QuizQuestionDataStore : QuizQuestionDataStoreProtocol {

    data class DTO(
        val index: Int,
        val periodName: String,
        val category: Question.Category
    )

    override suspend fun getQuestionDefinitions(periodId: Int): List<DTO> {
        return when (periodId) {
            1 -> getHadeanQuestionDefinitions()
            2 -> getArcheanQuestionDefinitions()
            3 -> getProterozoicQuestionDefinitions()
            4 -> getCambrianQuestionDefinitions()
            5 -> getOrdovicianQuestionDefinitions()
            6 -> getSilurianQuestionDefinitions()
            7 -> getDevonianQuestionDefinitions()
            8 -> getCarboniferousQuestionDefinitions()
            9 -> getPermianQuestionDefinitions()
            10 -> getTriassicQuestionDefinitions()
            11 -> getJurassicQuestionDefinitions()
            12 -> getCretaceousQuestionDefinitions()
            13 -> getPaleogeneQuestionDefinitions()
            14 -> getNeogeneQuestionDefinitions()
            15 -> getQuaternaryQuestionDefinitions()
            16 -> getFutureQuestionDefinitions()
            else -> emptyList()
        }
    }

    private fun getHadeanQuestionDefinitions(): List<DTO> =
        (1..10).map { DTO(it, "hadean", Question.Category.PRECAMBRIAN) }

    private fun getArcheanQuestionDefinitions(): List<DTO> =
        (1..32).map { DTO(it, "archean", Question.Category.PRECAMBRIAN) }

    private fun getProterozoicQuestionDefinitions(): List<DTO> =
        (1..70).map { DTO(it, "proterozoic", Question.Category.PRECAMBRIAN) }

    private fun getCambrianQuestionDefinitions(): List<DTO> =
        (1..94).map { DTO(it, "cambrian", Question.Category.PALEOZOIC) }

    private fun getOrdovicianQuestionDefinitions(): List<DTO> =
        (1..57).map { DTO(it, "ordovician", Question.Category.PALEOZOIC) }

    private fun getSilurianQuestionDefinitions(): List<DTO> =
        (1..58).map { DTO(it, "silurian", Question.Category.PALEOZOIC) }

    private fun getDevonianQuestionDefinitions(): List<DTO> =
        (1..117).map { DTO(it,"devonian", Question.Category.PALEOZOIC) }

    private fun getCarboniferousQuestionDefinitions(): List<DTO> =
        (1..116).map { DTO(it, "carboniferous", Question.Category.PALEOZOIC) }

    private fun getPermianQuestionDefinitions(): List<DTO> =
        (1..82).map { DTO(it, "permian", Question.Category.PALEOZOIC) }

    private fun getTriassicQuestionDefinitions(): List<DTO> =
        (1..137).map { DTO(it, "triassic", Question.Category.MESOZOIC) }

    private fun getJurassicQuestionDefinitions(): List<DTO> =
        (1..153).map { DTO(it, "jurassic", Question.Category.MESOZOIC) }

    private fun getCretaceousQuestionDefinitions(): List<DTO> =
        (1..226).map { DTO(it, "cretaceous", Question.Category.MESOZOIC) }

    private fun getPaleogeneQuestionDefinitions(): List<DTO> =
        (1..106).map { DTO(it, "paleogene", Question.Category.CENOZOIC) }

    private fun getNeogeneQuestionDefinitions(): List<DTO> =
        (1..91).map { DTO(it, "neogene", Question.Category.CENOZOIC) }

    private fun getQuaternaryQuestionDefinitions(): List<DTO> =
        (1..148).map { DTO(it, "quaternary", Question.Category.CENOZOIC) }

    private fun getFutureQuestionDefinitions(): List<DTO> =
        (1..10).map { DTO(it, "future", Question.Category.CENOZOIC) }
}