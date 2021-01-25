package com.zigis.paleontologas.launcher.fragments

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.zigis.paleontologas.application.android.BaseViewModelFragment
import com.zigis.paleontologas.application.entities.TaskStatus.*
import com.zigis.paleontologas.launcher.viewmodels.LauncherViewModel
import com.zigis.paleontologas.launcher.views.LauncherView
import com.zigis.paleontologas.main.activities.MainActivity

class LauncherFragment : BaseViewModelFragment<LauncherViewModel, LauncherView>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): LauncherView {
        return LauncherView(inflater.context).also {
            viewModel.synchronizeData()
        }
    }

    override fun observeChanges() {
        viewModel.synchronizationStatus.observe(viewLifecycleOwner) { taskStatus ->
            when (taskStatus) {
                is Success -> startActivity(Intent(activity, MainActivity::class.java))
                is Failure -> Toast.makeText(activity, taskStatus.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}