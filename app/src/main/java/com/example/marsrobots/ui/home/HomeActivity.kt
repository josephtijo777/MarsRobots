package com.example.marsrobots.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.marsrobots.R
import com.example.marsrobots.databinding.ActivityHomeBinding
import com.example.marsrobots.ui.base.BaseActivity
import com.example.marsrobots.utils.addFragment
import com.example.marsrobots.viewmodels.home.HomeViewModel

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding

    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(HomeFragment.newInstance(), R.id.container, "HomeFragment",false)
    }
}