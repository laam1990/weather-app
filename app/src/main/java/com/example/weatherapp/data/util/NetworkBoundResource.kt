package com.example.weatherapp.data.util

import android.util.Log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

const val TAG = "NetworkBoundResource"

inline fun <ResultType, RequestType> networkResource(
    crossinline networkCall: suspend () -> RequestType,
    crossinline mapperResponse: (RequestType) -> ResultType,
    crossinline errorHandling: suspend (Throwable) -> ErrorTypeData
) = flow {
    emit(Resource.Loading())
    emit(Resource.Success(mapperResponse(networkCall())))
}.catch { throwable ->
    Log.w(TAG, "networkResource: Resource.Error", throwable)
    emit(Resource.Error(errorHandling(throwable)))
}