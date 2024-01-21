package com.zigis.paleontologas.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

fun Fragment.launchOnRepeat(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend () -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
            launch { block() }
        }
    }
}