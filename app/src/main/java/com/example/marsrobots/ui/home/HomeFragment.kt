package com.example.marsrobots.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marsrobots.common.ViewState
import com.example.marsrobots.databinding.FragmentHomeBinding
import com.example.marsrobots.ui.base.BaseFragment
import com.example.marsrobots.utils.ConnectionUtils
import com.example.marsrobots.viewmodels.home.HomeViewModel

class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: HomeListAdapter

    private val viewModel by lazy {
        activity?.let {
            ViewModelProvider(it, viewModelFactory)[HomeViewModel::class.java]
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HomeListAdapter(requireContext())
        setUpRecyclerView()
        callImageInfoApi()
        observeApiResponse()
        binding.swipeRefresh.setOnRefreshListener {
            callImageInfoApi()
        }
    }


    /**
     * Initiate API call for getting status
     */
    private fun callImageInfoApi() {
        if (ConnectionUtils.isInternetAvailable(requireContext())) {
            viewModel?.getImageInfo("mars", "image")
        } else {
            binding.swipeRefresh.isRefreshing = false
            viewModel?.getOfflineData()
        }
        viewModel?.viewState?.observe(viewLifecycleOwner, Observer {
            when (it ?: ViewState.LOADING) {
                ViewState.LOADING -> {
                    if (!binding.swipeRefresh.isRefreshing)
                        showProgress()
                }
                ViewState.LOADED -> {
                    hideProgress()
                    binding.swipeRefresh.isRefreshing = false
                }
                ViewState.ERROR -> {
                    hideProgress()
                    binding.swipeRefresh.isRefreshing = false
                    Toast.makeText(requireContext(), viewModel?.errorMessage, Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    /**
     * Observing API response
     */
    private fun observeApiResponse() {
        viewModel?.imageListLiveData?.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                binding.textError.visibility = View.VISIBLE
            } else {
                binding.textError.visibility = View.GONE
                adapter.submitList(it)
            }
        })
    }

    /**
     * RecyclerView SetUp
     */
    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }
}