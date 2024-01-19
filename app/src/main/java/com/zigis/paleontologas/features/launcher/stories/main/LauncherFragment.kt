package com.zigis.paleontologas.features.launcher.stories.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.entities.TaskStatus.*
import com.zigis.paleontologas.features.main.MainActivity

class LauncherFragment : BaseFragment<LauncherViewModel, LauncherView>() {

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