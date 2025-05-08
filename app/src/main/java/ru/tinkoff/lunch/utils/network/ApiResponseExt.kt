package ru.tinkoff.lunch.utils.network

import org.json.JSONException
import org.json.JSONObject

fun getErrorMessage(errorBody: String?): String? {
    if (errorBody == null) return null
    return try {
        JSONObject(errorBody.trim()).getString("message")
    } catch (_: JSONException) {
        null
    }
}
