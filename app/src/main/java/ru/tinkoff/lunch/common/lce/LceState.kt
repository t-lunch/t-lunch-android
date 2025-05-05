package ru.tinkoff.lunch.common.lce

sealed interface LceState<out T> {

    /**
     * Состояние загрузки данных
     */
    object Loading : LceState<Nothing>

    /**
     * Состояние успешной загрузки с данными
     * @param data Загруженные данные
     */
    data class Content<T>(val data: T) : LceState<T>

    /**
     * Состояние ошибки
     * @param cause Причина ошибки
     */
    data class Error(val cause: Throwable) : LceState<Nothing>
}
