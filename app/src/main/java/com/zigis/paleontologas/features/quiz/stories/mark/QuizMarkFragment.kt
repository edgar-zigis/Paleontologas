package com.zigis.paleontologas.features.quiz.stories.mark

import android.view.LayoutInflater
import android.view.ViewGroup
import com.evernote.android.state.State
import com.zigis.paleontologas.core.architecture.BaseStatelessFragment

class QuizMarkFragment : BaseStatelessFragment<QuizMarkView>(), QuizMarkViewDelegate {

    @State var mark = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): QuizMarkView {
        return QuizMarkView(inflater.context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        contentView.setEvaluation(mark)
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