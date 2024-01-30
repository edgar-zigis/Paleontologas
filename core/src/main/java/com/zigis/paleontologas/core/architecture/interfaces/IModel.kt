package com.zigis.paleontologas.core.architecture.interfaces

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow

interface IModel<S: IState, I: IIntent> {
    val intents: Channel<I>
    val state: StateFlow<S?>
}