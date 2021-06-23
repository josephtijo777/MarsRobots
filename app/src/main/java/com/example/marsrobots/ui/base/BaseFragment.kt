package com.example.marsrobots.ui.base

import androidx.core.content.ContextCompat
import com.example.marsrobots.R
import com.example.marsrobots.application.arch.ViewModelFactory
import com.example.ticketapp.views.LoadingIndicator
import com.example.ticketapp.views.LoadingIndicatorConfig
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var loadingIndicator: LoadingIndicator? = null

    fun showProgress() {
        if (loadingIndicator == null) {
            loadingIndicator = LoadingIndicator.getInstance(
                requireContext(),
                LoadingIndicatorConfig(
                    progressMessage = "",
                    progressColor = ContextCompat.getColor(requireContext(), R.color.killarney)
                )
            )
            requireContext().runCatching {
                loadingIndicator?.show()
            }
        }
    }

    fun hideProgress() {
        loadingIndicator?.hide()
        loadingIndicator = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideProgress()
    }
}