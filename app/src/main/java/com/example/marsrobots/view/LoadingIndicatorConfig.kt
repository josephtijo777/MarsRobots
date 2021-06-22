package com.example.ticketapp.views

import android.graphics.Color

/**
 * Configuration class for [LoadingIndicator].
 * Set [LoadingIndicator] properties such as progress message, color etc.
 * If uou don't provide the configuration while using [LoadingIndicator], default configuration will be used.
 */
class LoadingIndicatorConfig(
    val progressMessage: String = "Loading...",
    val progressColor: Int = Color.GREEN,
    val isCancellable: Boolean = false
)