package com.ninjax.weather.data.source.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

abstract class SafeApi {

    suspend inline fun <T> safeApiCall(crossinline callFunction: suspend () -> T): ResultWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultWrapper.Success(callFunction.invoke())
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val response: Response<*>? = e.response()
                        when (response?.code()) {
                            HttpsURLConnection.HTTP_BAD_REQUEST -> {
                                ResultWrapper.GenericError()
                            }
                            HttpsURLConnection.HTTP_INTERNAL_ERROR -> {
                                ResultWrapper.GenericError()
                            }
                            else -> {
                                ResultWrapper.GenericError()
                            }
                        }

                    }
                    is IOException -> {
                        when (e) {
                            is UnknownHostException -> {
                                ResultWrapper.NetworkError
                            }
                            is SocketTimeoutException -> {
                                ResultWrapper.NetworkError
                            }
                            else -> {
                                ResultWrapper.NetworkError
                            }
                        }
                    }
                    else -> ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}
