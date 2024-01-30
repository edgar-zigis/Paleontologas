package com.zigis.paleontologas.core.interfaces

interface Navigable {
    fun onAttached()
    fun onBackPressed(): Boolean
}