package com.zigis.paleontologas.core.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.core.architecture.interfaces.IEvent
import com.zigis.paleontologas.core.architecture.interfaces.IIntent
import com.zigis.paleontologas.core.architecture.interfaces.IState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModelV2<S : IState, I : IIntent, E : IEvent> : ViewModel() {

    protected abstract val initialState: S
    abstract val state: StateFlow<S>

    private val eventsChannel = Channel<E>(Channel.BUFFERED)
    private val intentFlow = MutableSharedFlow<I>(extraBufferCapacity = 32)

    init {
        intentFlow.onEach {
            handleIntent(it)
        }.launchIn(viewModelScope)
    }

    fun sendIntent(intent: I) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }

    suspend fun collectEvents(collector: FlowCollector<E>) {
        withContext(Dispatchers.Main.immediate) {
            eventsChannel.receiveAsFlow().collect(collector)
        }
    }

    protected suspend fun sendEvent(event: E) {
        withContext(Dispatchers.Main.immediate) {
            eventsChannel.send(event)
        }
    }

    protected abstract suspend fun handleIntent(intent: I)

    protected fun <S : IState> Flow<S>.stateIn(
        viewModel: BaseViewModelV2<S, *, *>
    ): StateFlow<S> {
        return stateIn(
            scope = viewModel.viewModelScope,
            started = SharingStarted.WhileSubscribed(3000),
            initialValue = viewModel.initialState
        )
    }
}
