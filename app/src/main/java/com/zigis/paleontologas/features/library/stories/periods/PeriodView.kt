package com.zigis.paleontologas.features.library.stories.periods

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.core.extensions.dp
import com.zigis.paleontologas.core.extensions.getDrawable
import com.zigis.paleontologas.core.extensions.getString
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.core.ui.recyclerview.GridSpacingItemDecoration
import com.zigis.paleontologas.databinding.ViewParallaxFragmentBinding
import com.zigis.paleontologas.databinding.ViewParallaxHeaderBinding
import com.zigis.paleontologas.databinding.ViewPeriodContentBinding
import uk.co.senab.photoview.PhotoViewAttacher

class PeriodView(context: Context) : BaseView<PeriodViewState, ViewParallaxFragmentBinding>(context) {

    companion object {
        private const val lifeFormColumnCount = 2
    }

    var delegate: PeriodViewDelegate? = null

    override var binding: ViewParallaxFragmentBinding? = ViewParallaxFragmentBinding.inflate(layoutInflater)

    private val zoomViewBinding = ViewParallaxHeaderBinding.inflate(LayoutInflater.from(context))
    private val contentViewBinding = ViewPeriodContentBinding.inflate(LayoutInflater.from(context))

    private val adapter = LifeFormListAdapter {
        delegate?.openLifeForm(it)
    }

    init {
        with(requireBinding()) {
            title.text = getString(R.string.about_app)
            backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
            parallaxScroller.zoomView = zoomViewBinding.root
            parallaxScroller.setScrollContentView(contentViewBinding.root)
            with(contentViewBinding) {
                lifeFormList.layoutManager = GridLayoutManager(
                    context,
                    lifeFormColumnCount
                )
                lifeFormList.addItemDecoration(
                    GridSpacingItemDecoration(
                        columns = lifeFormColumnCount,
                        spacing = dp(6),
                        includeEdge = false
                    )
                )
                lifeFormList.adapter = adapter
            }
        }
        addView(requireBinding().root)
    }

    override fun render(state: PeriodViewState) {
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
            environmentInfo.text = context.getString(state.environmentDescription)
            descriptionInfo.text = context.getString(state.description)

            if (state.map.isNotBlank()) {
                mapContainer.visibility = VISIBLE
                mapView.setImageDrawable(context.getDrawable(state.map))
                mapAuthor.text = when (state.periodId) {
                    3 -> context.getString(R.string.map_author_1)
                    else -> context.getString(R.string.map_author_2)
                }
                PhotoViewAttacher(mapView).update()
            }

            if (context.getString(state.additionalTitle).isNotBlank()) {
                additionalHeading.text = context.getString(state.additionalTitle)
                additionalInfo.text = context.getString(state.additionalDescription)
                additionalHeading.visibility = VISIBLE
                additionalInfo.visibility = VISIBLE
            }

            lifeFormInfo.text = context.getString(state.lifeFormDescription)
        }
        adapter.updateItems(state.lifeForms)
    }
}