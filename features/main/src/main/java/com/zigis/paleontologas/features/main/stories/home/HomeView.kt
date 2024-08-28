package com.zigis.paleontologas.features.main.stories.home

import android.content.Context
import android.view.LayoutInflater
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.nightonke.boommenu.BoomMenuButton
import com.nightonke.boommenu.Eases.EaseType
import com.nightonke.boommenu.Types.BoomType
import com.nightonke.boommenu.Types.ButtonType
import com.nightonke.boommenu.Types.ClickEffectType
import com.nightonke.boommenu.Types.DimType
import com.nightonke.boommenu.Types.OrderType
import com.nightonke.boommenu.Types.PlaceType
import com.nightonke.boommenu.Util
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.features.main.R
import com.zigis.paleontologas.features.main.databinding.FragmentHomeBinding
import com.zigis.paleontologas.features.main.stories.home.adapter.HomeListAdapter


class HomeView(
    context: Context
) : BaseView<HomeViewState, FragmentHomeBinding>(context) {

    var delegate: HomeViewDelegate? = null

    override var binding: FragmentHomeBinding? = FragmentHomeBinding.inflate(LayoutInflater.from(context))

    private val adapter = HomeListAdapter(emptyList()) {
        delegate?.openPeriod(it)
    }

    init {
        with(requireBinding()) {
            val pieceColor = ContextCompat.getColor(context, R.color.colorMenuItem)
            BoomMenuButton.Builder()
                .addSubButton(context, R.drawable.menu_time_scale, intArrayOf(pieceColor, pieceColor), "")
                .addSubButton(context, R.drawable.menu_quiz, intArrayOf(pieceColor, pieceColor), "")
                .addSubButton(context, R.drawable.menu_language, intArrayOf(pieceColor, pieceColor), "")
                .addSubButton(context, R.drawable.menu_about, intArrayOf(pieceColor, pieceColor), "")
                .frames(80)
                .duration(250)
                .delay(100)
                .showOrder(OrderType.RANDOM)
                .hideOrder(OrderType.RANDOM)
                .button(ButtonType.CIRCLE)
                .boom(BoomType.LINE)
                .place(PlaceType.CIRCLE_4_2)
                .showMoveEase(EaseType.EaseOutBack)
                .hideMoveEase(EaseType.EaseOutCirc)
                .showScaleEase(EaseType.EaseOutBack)
                .hideScaleType(EaseType.EaseOutCirc)
                .rotateDegree(720)
                .showRotateEase(EaseType.EaseOutBack)
                .hideRotateType(EaseType.Linear)
                .autoDismiss(true)
                .cancelable(true)
                .dim(DimType.DIM_6)
                .clickEffect(ClickEffectType.RIPPLE)
                .boomButtonShadow(Util.getInstance().dp2px(2f), Util.getInstance().dp2px(2f))
                .subButtonsShadow(Util.getInstance().dp2px(2f), Util.getInstance().dp2px(2f))
                .animator(null)
                .onSubButtonClick { index ->
                    when (index) {
                        1 -> delegate?.openQuiz()
                        2 -> delegate?.openLanguages()
                        3 -> delegate?.openAbout()
                    }
                }
                .init(menuButton)

            periodList.adapter = adapter
            periodList.layoutManager = LinearLayoutManager(context)
            periodList.addItemDecoration(DividerItemDecoration(context, VERTICAL).also {
                it.setDrawable(ContextCompat.getDrawable(context, R.drawable.list_divider)!!)
            })
        }
        addView(requireBinding().root)
    }

    override fun render(state: HomeViewState) = with(requireBinding()) {
        if (state.animateLayoutChanges == true) {
            periodList.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
            periodList.scheduleLayoutAnimation()
        }
        adapter.updateItems(items = state.periodItems)
        if (state.animateLayoutChanges == true) {
            AlphaAnimation(1f, 0f).apply {
                duration = 500
                fillAfter = true
                logoImage.startAnimation(this)
            }
        }
    }
}