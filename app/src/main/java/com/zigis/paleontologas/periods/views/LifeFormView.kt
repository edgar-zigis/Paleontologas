package com.zigis.paleontologas.periods.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.application.extensions.getDrawable
import com.zigis.paleontologas.application.extensions.getString
import com.zigis.paleontologas.databinding.ViewLifeformContentBinding
import com.zigis.paleontologas.databinding.ViewParallaxFragmentBinding
import com.zigis.paleontologas.databinding.ViewParallaxHeaderBinding
import com.zigis.paleontologas.periods.data.entities.LifeForm
import uk.co.senab.photoview.PhotoViewAttacher

class LifeFormView(context: Context) : BaseView<ViewParallaxFragmentBinding>(
    context,
    ViewParallaxFragmentBinding.inflate(LayoutInflater.from(context))
) {
    override val titleTextResId: Int = R.string.app_name

    private val zoomViewBinding = ViewParallaxHeaderBinding.inflate(LayoutInflater.from(context))
    private val contentViewBinding = ViewLifeformContentBinding.inflate(LayoutInflater.from(context))

    override fun initialize() = with(viewBinding) {
        parallaxScroller.zoomView = zoomViewBinding.root
        parallaxScroller.setScrollContentView(contentViewBinding.root)
    }

    fun configureWith(lifeForm: LifeForm) {
        setTitle(context.getString(lifeForm.title))
        with(zoomViewBinding) {
            imageView.setImageDrawable(context.getDrawable(lifeForm.artwork))
            photoAuthor.text = lifeForm.artworkAuthor
        }
        with(contentViewBinding) {
            title.text = context.getString(lifeForm.title)
            timeScale.text = context.getString(R.string.mya, lifeForm.timeScale)
            descriptionInfo.text = context.getString(lifeForm.description)

            if (lifeForm.additionalArtwork.isNotEmpty()) {
                additionalImageContainer.visibility = View.VISIBLE
                additionalImageAuthor.text = lifeForm.additionalArtworkAuthor
                additionalImage.setImageDrawable(context.getDrawable(lifeForm.additionalArtwork))
                PhotoViewAttacher(additionalImage).update()
            }
        }
    }
}