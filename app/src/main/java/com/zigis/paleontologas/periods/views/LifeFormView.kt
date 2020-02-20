package com.zigis.paleontologas.periods.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.application.extensions.getDrawable
import com.zigis.paleontologas.application.extensions.getString
import com.zigis.paleontologas.periods.data.entities.LifeForm
import kotlinx.android.synthetic.main.view_lifeform_content.view.*
import kotlinx.android.synthetic.main.view_lifeform_content.view.title
import kotlinx.android.synthetic.main.view_parallax_fragment.view.*
import kotlinx.android.synthetic.main.view_parallax_header.view.*
import uk.co.senab.photoview.PhotoViewAttacher

class LifeFormView(context: Context) : BaseView(context, R.layout.view_parallax_fragment) {

    private lateinit var zoomView: View
    private lateinit var contentView: View

    override fun initialize() {
        val inflater = LayoutInflater.from(context)
        zoomView = inflater.inflate(R.layout.view_parallax_header, this, false).also {
            parallaxScroller.zoomView = it
        }
        contentView = inflater.inflate(R.layout.view_lifeform_content, this, false).also {
            parallaxScroller.setScrollContentView(it)
        }
    }

    fun configureWith(lifeForm: LifeForm) {
        title.text = context.getString(lifeForm.title)

        zoomView.imageView.setImageDrawable(context.getDrawable(lifeForm.artwork))
        zoomView.photoAuthor.text = lifeForm.artworkAuthor

        with(contentView) {
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