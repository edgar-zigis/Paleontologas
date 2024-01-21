package com.zigis.paleontologas.core.architecture.v2.interfaces

interface IView<S: IState> {
    fun render(state: S)
}