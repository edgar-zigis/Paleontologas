package com.zigis.paleontologas.core.extensions.android

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.MutableLiveData

class DistinctLiveData<T> : MutableLiveData<T>() {

    @MainThread
    override fun setValue(@Nullable t: T?) {
        if (t != value) {
            super.setValue(t)
        }
    }
}