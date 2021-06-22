package com.example.marsrobots.utils

import java.text.SimpleDateFormat
import java.util.*

object Helper {
    fun getErrorMessage(throwable: Throwable): String? {
        return throwable.message
    }

    fun convertDateToOutputFormat(
        dateString: String,
        inputFormat: String,
        outputFormat: String
    ): String {
        val inputFormatter = SimpleDateFormat(inputFormat, Locale.getDefault())
        val outFormatter = SimpleDateFormat(outputFormat, Locale.getDefault())
        val date = inputFormatter.parse(dateString)
        return outFormatter.format(date)
    }
}