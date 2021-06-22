package com.example.marsrobots.ui.base

import com.example.marsrobots.application.arch.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
}