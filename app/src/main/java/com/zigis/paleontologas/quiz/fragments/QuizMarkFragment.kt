package com.zigis.paleontologas.quiz.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.evernote.android.state.State
import com.zigis.paleontologas.application.android.BaseFragment
import com.zigis.paleontologas.quiz.views.QuizMarkView
import com.zigis.paleontologas.quiz.views.QuizMarkViewDelegate

class QuizMarkFragment : BaseFragment<QuizMarkView>(), QuizMarkViewDelegate {

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