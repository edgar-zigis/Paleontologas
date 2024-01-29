package com.zigis.paleontologas.features.library.stories.geologicalperiod

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
import com.zigis.paleontologas.databinding.FragmentGeologicalPeriodBinding
import com.zigis.paleontologas.databinding.ParallaxContainerBinding
import com.zigis.paleontologas.databinding.ParallaxHeaderBinding
import com.zigis.paleontologas.features.library.stories.geologicalperiod.adapter.GeologicalPeriodListAdapter
import uk.co.senab.photoview.PhotoViewAttacher

class GeologicalPeriodView(context: Context) : BaseView<GeologicalPeriodViewState, ParallaxContainerBinding>(context) {

    var delegate: GeologicalPeriodViewDelegate? = null

    override var binding: ParallaxContainerBinding? = ParallaxContainerBinding.inflate(layoutInflater)

    private val zoomViewBinding = ParallaxHeaderBinding.inflate(LayoutInflater.from(context))
    private val contentViewBinding = FragmentGeologicalPeriodBinding.inflate(LayoutInflater.from(context))

    private val adapter = GeologicalPeriodListAdapter {
        delegate?.openLifeForm(lifeFormId = it)
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
                    2
                )
                lifeFormList.addItemDecoration(
                    GridSpacingItemDecoration(
                        columns = 2,
                        spacing = dp(6),
                        includeEdge = false
                    )
                )
                lifeFormList.adapter = adapter
            }
        }
        addView(requireBinding().root)
    }

    override fun render(state: GeologicalPeriodViewState) {
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
        adapter.updateItems(state.lifeFormItems)
    }
}