package com.zigis.paleontologas.features.main.stories.home

import android.content.Context
import android.view.LayoutInflater
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton
import com.zigis.paleontologas.R
import com.zigis.paleontologas.databinding.ViewMainMenuBinding
import com.zigis.paleontologas.features.library.data.Period

class HomeView(context: Context) : FrameLayout(context) {

    var delegate: HomeViewDelegate? = null

    private val binding = ViewMainMenuBinding.inflate(LayoutInflater.from(context))

    private val adapter = PeriodListAdapter(emptyList()) {
        delegate?.openPeriod(it)
    }

    private var animateLayoutChanges = true

    init {
        initMenu()
        initPeriodList()
        addView(binding.root)
    }

    private fun initMenu() = with(binding) {
        for (i in 0 until menuButton.piecePlaceEnum.pieceNumber()) {
            val image = when (i) {
                0 -> R.drawable.menu_time_scale
                1 -> R.drawable.menu_quiz
                2 -> R.drawable.menu_language
                3 -> R.drawable.menu_about
                else -> 0
            }
            val builder = SimpleCircleButton.Builder().listener { index ->
                when (index) {
                    1 -> delegate?.openQuiz()
                    2 -> delegate?.openLanguages()
                    3 -> delegate?.openAbout()
                }
            }
                .rippleEffect(true)
                .normalColor(ContextCompat.getColor(context, R.color.colorMenuItem))
                .pieceColor(ContextCompat.getColor(context, R.color.white))
                .normalImageRes(image)
            menuButton.addBuilder(builder)
        }
    }

    private fun initPeriodList() = with(binding) {
        periodList.adapter = adapter
        periodList.layoutManager = LinearLayoutManager(context)
        periodList.addItemDecoration(DividerItemDecoration(context, VERTICAL).also {
            it.setDrawable(ContextCompat.getDrawable(context, R.drawable.list_divider)!!)
        })
    }

    fun setPeriods(items: List<Period>) = with(binding) {
        if (animateLayoutChanges) {
            periodList.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
            periodList.scheduleLayoutAnimation()
        }

        adapter.items = items
        adapter.notifyDataSetChanged()

        if (animateLayoutChanges) {
            AlphaAnimation(1f, 0f).apply {
                duration = 500
                fillAfter = true
                logoImage.startAnimation(this)
            }
            animateLayoutChanges = false
        }
    }
}