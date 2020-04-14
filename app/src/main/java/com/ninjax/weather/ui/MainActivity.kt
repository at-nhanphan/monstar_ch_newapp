package com.ninjax.weather.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ninjax.weather.R
import com.ninjax.weather.extension.replaceFragment
import com.ninjax.weather.ui.home.HomeFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        // add view
        addView()
    }

    private fun addView() {
        replaceFragment(HomeFragment())
    }
}