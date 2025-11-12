package com.bashar.abnrepositories.src.core.utils

import android.util.Log
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

object ApiErrorParser {
    fun getMessage(throwable: Throwable): String {
        return when (throwable) {
            is HttpException -> {
                try {
                    val errorJson = throwable.response()?.errorBody()?.string()
                    if (!errorJson.isNullOrEmpty()) {
                        val json = JSONObject(errorJson)
                        json.optString("message", "Unknown error")
                    } else {
                        "Unknown server error"
                    }
                } catch (e: Exception) {
                    Log.e("ApiErrorParser", "Error parsing HTTP error", e)
                    "Unknown error"
                }
            }

            is IOException -> "Network connection error"
            else -> throwable.localizedMessage ?: "Unexpected error occurred"
        }
    }
}
