package com.zigis.paleontologas.features.library.stories.formavitae

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.core.extensions.getDrawable
import com.zigis.paleontologas.core.extensions.getString
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewLifeformContentBinding
import com.zigis.paleontologas.databinding.ViewParallaxFragmentBinding
import com.zigis.paleontologas.databinding.ViewParallaxHeaderBinding
import uk.co.senab.photoview.PhotoViewAttacher

class FormaVitaeView(context: Context) : BaseView<FormaVitaeViewState, ViewParallaxFragmentBinding>(context) {

    var delegate: FormaVitaeViewDelegate? = null

    override var binding: ViewParallaxFragmentBinding? = ViewParallaxFragmentBinding.inflate(layoutInflater)

    private val zoomViewBinding = ViewParallaxHeaderBinding.inflate(LayoutInflater.from(context))
    private val contentViewBinding = ViewLifeformContentBinding.inflate(LayoutInflater.from(context))

    init {
        with(requireBinding()) {
            title.text = getString(R.string.about_app)
            backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
            parallaxScroller.zoomView = zoomViewBinding.root
            parallaxScroller.setScrollContentView(contentViewBinding.root)
        }
        addView(requireBinding().root)
    }

    override fun render(state: FormaVitaeViewState) {
        with(requireBinding()) {
            title.text = context.getString(state.title)
        }
        with(zoomViewBinding) {
            imageView.setImageDrawable(context.getDrawable(state.artwork))
            photoAuthor.text = state.artworkAuthor
        }
        with(contentViewBinding) {
            title.text = context.getString(state.title)
            timeScale.text = context.getString(R.string.mya, state.timeScale)
            descriptionInfo.text = context.getString(state.description)

            if (state.additionalArtwork.isNotEmpty()) {
                additionalImageContainer.visibility = View.VISIBLE
                additionalImageAuthor.text = state.additionalArtworkAuthor
                additionalImage.setImageDrawable(context.getDrawable(state.additionalArtwork))
                PhotoViewAttacher(additionalImage).update()
            }
        }
    }
}