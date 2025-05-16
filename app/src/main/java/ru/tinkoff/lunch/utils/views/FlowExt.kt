package ru.tinkoff.lunch.utils.views

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

inline fun <reified T> Flow<T>.collectOnViewLifecycle(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.RESUMED,
    collector: FlowCollector<T>
) {
    lifecycleOwner.lifecycleScope.launch {
        this@collectOnViewLifecycle
            .flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
            .collect(collector)
    }
}
