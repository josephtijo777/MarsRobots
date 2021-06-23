package com.example.marsrobots.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.marsrobots.constants.SPLASH_TIME
import com.example.marsrobots.databinding.ActivitySplashBinding
import com.example.marsrobots.ui.base.BaseActivity
import com.example.marsrobots.ui.home.HomeActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : BaseActivity() {
    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moveToHomeScreen()
    }

    /**
     * Redirects to Home Screen after a delay
     */
    private fun moveToHomeScreen() {
        compositeDisposable.add(
            Observable.timer(SPLASH_TIME, TimeUnit.SECONDS)
                .subscribe {
                    openHomeScreen()
                })
    }

    private fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}