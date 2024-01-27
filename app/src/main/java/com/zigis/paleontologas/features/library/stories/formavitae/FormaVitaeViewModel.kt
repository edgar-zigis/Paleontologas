package com.zigis.paleontologas.features.library.stories.formavitae

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.library.repositories.LifeFormRepository
import com.zigis.paleontologas.features.library.stories.formavitae.FormaVitaeIntent.*

class FormaVitaeViewModel(
    private val lifeFormRepository: LifeFormRepository
) : BaseViewModel<FormaVitaeViewState, FormaVitaeIntent>() {

    override fun getInitialData() = FormaVitaeViewState()

    override suspend fun handleIntent(intent: FormaVitaeIntent) {
        when (intent) {
            is Initialize -> initialize(lifeFormId = intent.lifeFormId)
        }
    }

    private suspend fun initialize(lifeFormId: Int) {
        val fetchedLifeForm = lifeFormRepository.findOne(lifeFormId)
        updateState {
            it.copy(
                title = fetchedLifeForm.title,
                artwork = fetchedLifeForm.artwork,
                artworkAuthor = fetchedLifeForm.artworkAuthor,
                timeScale = fetchedLifeForm.timeScale,
                description = fetchedLifeForm.description,
                additionalArtworkAuthor = fetchedLifeForm.additionalArtworkAuthor,
                additionalArtwork = fetchedLifeForm.additionalArtwork
            )
        }
    }
}