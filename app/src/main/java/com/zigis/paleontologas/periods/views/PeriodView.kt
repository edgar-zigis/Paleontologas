package com.zigis.paleontologas.periods.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.application.extensions.dp
import com.zigis.paleontologas.application.extensions.getDrawable
import com.zigis.paleontologas.application.extensions.getString
import com.zigis.paleontologas.application.ui.recyclerview.GridSpacingItemDecoration
import com.zigis.paleontologas.periods.adapters.LifeFormListAdapter
import com.zigis.paleontologas.periods.data.entities.LifeForm
import com.zigis.paleontologas.periods.data.entities.Period
import kotlinx.android.synthetic.main.view_parallax_fragment.view.*
import kotlinx.android.synthetic.main.view_period_content.view.*
import kotlinx.android.synthetic.main.view_parallax_header.view.*
import kotlinx.android.synthetic.main.view_period_content.view.title
import uk.co.senab.photoview.PhotoViewAttacher

interface PeriodViewDelegate {
    fun openLifeForm(lifeForm: LifeForm)
}

class PeriodView(context: Context) : BaseView(context, R.layout.view_parallax_fragment) {

    companion object {
        private const val lifeFormColumnCount = 2
    }

    var delegate: PeriodViewDelegate? = null

    private lateinit var zoomView: View
    private lateinit var contentView: View

    override fun initialize() {
        val inflater = LayoutInflater.from(context)
        zoomView = inflater.inflate(R.layout.view_parallax_header, this, false).also {
            parallaxScroller.zoomView = it
        }
        contentView = inflater.inflate(R.layout.view_period_content, this, false).also {
            parallaxScroller.setScrollContentView(it)
        }
        initLifeFormList()
    }

    fun configureWith(period: Period) {
        title.text = context.getString(period.title)

        zoomView.imageView.setImageDrawable(context.getDrawable(period.artwork))
        zoomView.photoAuthor.text = period.artworkAuthor

        with(contentView) {
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
        lifeFormList.adapter =
            LifeFormListAdapter(items) {
                delegate?.openLifeForm(it)
            }
    }

    private fun initLifeFormList() {
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
    }
}