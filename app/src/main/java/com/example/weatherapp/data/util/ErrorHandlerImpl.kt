package com.example.weatherapp.data.util

import android.util.Log
import com.example.weatherapp.data.model.ErrorBodyData
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.net.HttpURLConnection

class ErrorHandlerImpl : ErrorHandler {

    override suspend fun getError(throwable: Throwable): ErrorTypeData =
        when (throwable) {
            is HttpException -> {
                val responseBody: ResponseBody? = throwable.response()?.errorBody()
                val errorBodyData = getErrorBodyData(responseBody)
                when (throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorTypeData.ConnectionError(errorBodyData)
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorTypeData.ConnectionError(
                        errorBodyData
                    )
                    else -> ErrorTypeData.ControlledError(errorBodyData)
                }
            }
            else -> {
                //Return error common for other kind
                ErrorTypeData.ConnectionError()
            }
        }

    private fun getErrorBodyData(responseBody: ResponseBody?) = try {
        responseBody?.string()?.deserializeObject<ErrorBodyData>()
    } catch (e: JsonSyntaxException) {
        Log.d(ErrorHandlerImpl::class.simpleName.orEmpty(), "$e")
        null
    }
}

inline fun <reified T> String.deserializeObject(): T? = Gson().fromJson(this, T::class.java)