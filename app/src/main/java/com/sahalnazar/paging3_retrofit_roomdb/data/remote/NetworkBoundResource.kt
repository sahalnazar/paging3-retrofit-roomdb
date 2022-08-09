package com.sahalnazar.paging3_retrofit_roomdb.data.remote

import com.sahalnazar.paging3_retrofit_roomdb.util.BaseResult
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    emit(BaseResult.loading(null))
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(BaseResult.loading(data))

        try {
            saveFetchResult(fetch())
            query().map { BaseResult.success(it, null) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { BaseResult.error(throwable.toString(), it, null) }
        }
    } else {
        query().map { BaseResult.success(it, null) }
    }

    emitAll(flow)
}