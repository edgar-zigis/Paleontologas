package com.zigis.paleontologas.features.library.stories.lifeforms

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.library.repositories.LifeFormRepository

class LifeFormViewModel(
    private val lifeFormRepository: LifeFormRepository
) : BaseViewModel<LifeFormViewState, LifeFormIntent>() {

    override fun getInitialData() = LifeFormViewState()

    override suspend fun handleIntent(intent: LifeFormIntent) {
        when (intent) {
            is LifeFormIntent.Initialize -> initialize(lifeFormId = intent.lifeFormId)
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