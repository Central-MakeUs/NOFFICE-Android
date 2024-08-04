package com.easyhz.noffice.core.network.converter

import com.easyhz.noffice.core.network.model.response.ErrorResponse
import com.google.gson.Gson

class ErrorResponseConverter constructor(
    private val gson: Gson
) {
    fun fromJsonToErrorResponse(json: String): ErrorResponse {
        return gson.fromJson(json, ErrorResponse::class.java)
    }
}