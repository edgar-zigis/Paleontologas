package com.zigis.paleontologas.application.interfaces

interface Navigable {
    fun onAttached()
    fun onBackPressed(): Boolean
}