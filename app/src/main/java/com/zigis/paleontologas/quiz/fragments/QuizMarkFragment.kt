package com.zigis.paleontologas.quiz.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.evernote.android.state.State
import com.zigis.paleontologas.application.android.BaseFragment
import com.zigis.paleontologas.quiz.views.QuizMarkView

class QuizMarkFragment : BaseFragment<QuizMarkView>() {

    @State var mark = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): QuizMarkView {
        return QuizMarkView(inflater.context).also {
            it.onBack = {
                onBackPressed()
            }
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
}