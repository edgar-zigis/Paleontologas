package com.zigis.paleontologas.features.quiz.stories.mark

import android.content.Context
import com.zigis.paleontologas.core.architecture.v2.BaseFragment
import com.zigis.paleontologas.core.architecture.v2.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import org.koin.android.ext.android.inject

class QuizMarkFragment : BaseFragment<QuizMarkViewState, QuizMarkIntent, QuizMarkViewModel>(),
    QuizMarkViewDelegate {

    var mark: Int by savedState(0)

    override val viewModel: QuizMarkViewModel by inject()

    override fun onCreateView(context: Context): IView<QuizMarkViewState> {
        return QuizMarkView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.intents.sendSafely(QuizMarkIntent.Initialize(mark = mark))
    }

    override fun onBackPressed(): Boolean {
        activity?.supportFragmentManager?.popBackStackImmediate()
        activity?.supportFragmentManager?.popBackStackImmediate()
        return true
    }

    //  QuizMarkViewDelegate

    override fun onBackInvoked() {
        onBackPressed()
    }
}