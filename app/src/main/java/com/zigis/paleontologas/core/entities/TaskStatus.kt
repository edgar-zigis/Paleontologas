package com.zigis.paleontologas.core.entities

sealed class TaskStatus {

    object Success : TaskStatus()
    data class Failure(val message: String?) : TaskStatus()

    companion object {
        fun success() = Success
        fun failure(message: String?) = Failure(message)
    }
}