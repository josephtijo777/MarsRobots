package com.example.ticketapp.views

import android.app.AlertDialog
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import com.example.marsrobots.R
import com.example.marsrobots.databinding.LoadingIndicatorViewBinding


/**
 * Helper for showing loading indicator.
 * Shows progress dialog with a message.
 * Can set properties via [LoadingIndicatorConfig].
 */
object LoadingIndicator {

    private var alertDialog: AlertDialog? = null
    private lateinit var binding: LoadingIndicatorViewBinding

    /**
     * Return an instance of [LoadingIndicator] with specified [LoadingIndicatorConfig]
     */
    fun getInstance(
        context: Context,
        loadingIndicatorConfig: LoadingIndicatorConfig
    ): LoadingIndicator {
        val layoutInflater = LayoutInflater.from(context)
        binding = LoadingIndicatorViewBinding.inflate(layoutInflater)
        binding.progress.indeterminateDrawable?.colorFilter =
            PorterDuffColorFilter(loadingIndicatorConfig.progressColor, PorterDuff.Mode.SRC_ATOP)
        binding.text.text = loadingIndicatorConfig.progressMessage
        val alertDialog = AlertDialog.Builder(context).setView(binding.root).create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(loadingIndicatorConfig.isCancellable)
        LoadingIndicator.alertDialog = alertDialog
        return this
    }

    /**
     * Return an instance of [LoadingIndicator] with default [LoadingIndicatorConfig]
     */
    fun getInstance(context: Context): LoadingIndicator {
        return getInstance(
            context,
            LoadingIndicatorConfig()
        )
    }

    /**
     * Show loading indicator
     */
    fun show() {
        alertDialog?.show()
    }

    /**
     * Hide loading indicator
     */
    fun hide() {
        alertDialog?.dismiss()
    }

    /**
     * To check if alert dialog is visible
     */
    fun isShowing() = alertDialog?.isShowing

    /**
     * Set progress indicator message
     */
    fun setMessage(message: String) {
        binding.root.findViewById<AppCompatTextView>(R.id.text)?.text = message
    }

}
