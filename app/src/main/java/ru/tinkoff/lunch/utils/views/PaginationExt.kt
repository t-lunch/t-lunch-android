package ru.tinkoff.lunch.utils.views

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey

@DslMarker
annotation class PagingDSL

@PagingDSL
class PagingHandlerScope<T : Any>(
    private val items: LazyPagingItems<T>
) {
    private var handled = false
    private val loadState = derivedStateOf { items.loadState }.value

    @Composable
    fun onEmpty(body: @Composable () -> Unit) {
        if (handled) return
        if (loadState.refresh !is LoadState.Error && items.itemCount == 0) {
            handled = true
            body()
        }
    }

    @Composable
    fun onRefresh(body: @Composable () -> Unit) {
        if (handled) return
        if (loadState.refresh is LoadState.Loading) {
            handled = true
            body()
        }
    }

    @Composable
    fun onSuccess(body: @Composable (LazyPagingItems<T>) -> Unit) {
        if (!handled) {
            handled = true
            body(items)
        }
    }

    @Composable
    fun onError(body: @Composable (Throwable) -> Unit) {
        if (handled) return
        if (loadState.refresh is LoadState.Error) {
            val error = (loadState.refresh as LoadState.Error).error
            handled = true
            body(error)
        } else this
    }

    @LazyScopeMarker
    fun LazyListScope.onAppendItem(body: @Composable LazyItemScope.() -> Unit) {
        if (loadState.append == LoadState.Loading) {
            item { body(this) }
        }
    }

    @LazyScopeMarker
    fun LazyListScope.onLastItem(body: @Composable LazyItemScope.() -> Unit) {
        if (loadState.append.endOfPaginationReached) item { body(this) }
    }

    @LazyScopeMarker
    fun LazyListScope.onPagingItems(
        key: ((T) -> Any)?,
        body: @Composable LazyItemScope.(T) -> Unit
    ) {
        items(
            count = items.itemCount,
            key = items.itemKey(key),
        ) { index ->
            val item = items[index]
            item?.let {
                body(it)
            }
        }
    }
}

@Composable
fun <T : Any> HandlePagingItems(
    items: LazyPagingItems<T>,
    content: @Composable PagingHandlerScope<T>.() -> Unit
) {
    PagingHandlerScope(items).content()
}
