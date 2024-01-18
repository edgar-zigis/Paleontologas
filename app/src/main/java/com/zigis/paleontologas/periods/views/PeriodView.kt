package com.zigis.paleontologas.periods.views

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.architecture.BaseView
import com.zigis.paleontologas.application.extensions.dp
import com.zigis.paleontologas.application.extensions.getDrawable
import com.zigis.paleontologas.application.extensions.getString
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.application.ui.recyclerview.GridSpacingItemDecoration
import com.zigis.paleontologas.databinding.ViewParallaxFragmentBinding
import com.zigis.paleontologas.databinding.ViewParallaxHeaderBinding
import com.zigis.paleontologas.databinding.ViewPeriodContentBinding
import com.zigis.paleontologas.periods.adapters.LifeFormListAdapter
import com.zigis.paleontologas.periods.data.entities.LifeForm
import com.zigis.paleontologas.periods.data.entities.Period
import uk.co.senab.photoview.PhotoViewAttacher

interface PeriodViewDelegate {
    fun openLifeForm(lifeForm: LifeForm)
    fun onBackInvoked()
}

class PeriodView(context: Context) : BaseView(context) {
    companion object {
        private const val lifeFormColumnCount = 2
    }

    var delegate: PeriodViewDelegate? = null

    override val binding = ViewParallaxFragmentBinding.inflate(layoutInflater)

    private val zoomViewBinding = ViewParallaxHeaderBinding.inflate(LayoutInflater.from(context))
    private val contentViewBinding = ViewPeriodContentBinding.inflate(LayoutInflater.from(context))

    private val adapter = LifeFormListAdapter {
        delegate?.openLifeForm(it)
    }

    init {
        with(binding) {
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
        addView(binding.root)
    }

    fun configureWith(period: Period) {
        with(binding) {
            title.text = context.getString(period.title)
        }
        with(zoomViewBinding) {
            imageView.setImageDrawable(context.getDrawable(period.artwork))
            photoAuthor.text = period.artworkAuthor
        }
        with(contentViewBinding) {
            title.text = context.getString(period.title)
            timeScale.text = context.getString(R.string.mya, period.timeScale)
            environmentInfo.text = context.getString(period.environmentDescription)
            descriptionInfo.text = context.getString(period.description)

            if (period.map.isNotBlank()) {
                mapContainer.visibility = VISIBLE
                mapView.setImageDrawable(context.getDrawable(period.map))
                mapAuthor.text = when (period.id) {
                    3 -> context.getString(R.string.map_author_1)
                    else -> context.getString(R.string.map_author_2)
                }
                PhotoViewAttacher(mapView).update()
            }

            if (context.getString(period.additionalTitle).isNotBlank()) {
                additionalHeading.text = context.getString(period.additionalTitle)
                additionalInfo.text = context.getString(period.additionalDescription)
                additionalHeading.visibility = VISIBLE
                additionalInfo.visibility = VISIBLE
            }

            lifeFormInfo.text = context.getString(period.lifeFormDescription)
        }
    }

    fun setLifeForms(items: List<LifeForm>) {
        adapter.updateItems(items)
    }
}