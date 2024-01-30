package com.zigis.paleontologas.core.architecture.interfaces

interface IView<S: IState> {
    fun render(state: S)
}