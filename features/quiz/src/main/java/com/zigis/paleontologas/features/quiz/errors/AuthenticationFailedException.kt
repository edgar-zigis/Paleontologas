package com.zigis.paleontologas.features.quiz.errors

sealed class AuthenticationFailedException(message: String? = null) : Exception(message) {
    data object NameBlackListedError : AuthenticationFailedException("error_account_name_not_allowed") {
        @Suppress("unused")
        private fun readResolve(): Any = NameBlackListedError
    }
    data object NameAlreadyExistsError : AuthenticationFailedException("error_account_name_already_exists") {
        @Suppress("unused")
        private fun readResolve(): Any = NameAlreadyExistsError
    }
    data object GoogleAuthenticationError : AuthenticationFailedException("Google Authentication Error!") {
        @Suppress("unused")
        private fun readResolve(): Any = GoogleAuthenticationError
    }
    data object NotAuthenticatedError : AuthenticationFailedException("Not authenticated!") {
        @Suppress("unused")
        private fun readResolve(): Any = NotAuthenticatedError
    }
}