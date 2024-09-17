package com.zigis.paleontologas.features.library.stories.lifeform

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.library.repositories.LifeFormRepository
import com.zigis.paleontologas.features.library.routing.LibraryRouter
import com.zigis.paleontologas.features.library.stories.lifeform.LifeFormScreenIntent.*

class LifeFormViewModel(
    private val libraryRouter: LibraryRouter,
    private val lifeFormRepository: LifeFormRepository
) : BaseViewModel<LifeFormScreenState, LifeFormScreenIntent>() {

    override fun getInitialData() = LifeFormScreenState()

    override suspend fun handleIntent(intent: LifeFormScreenIntent) {
        when (intent) {
            is Initialize -> initialize(lifeFormId = intent.lifeFormId)
            is InvokeBack -> libraryRouter.popCurrentScreen()
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