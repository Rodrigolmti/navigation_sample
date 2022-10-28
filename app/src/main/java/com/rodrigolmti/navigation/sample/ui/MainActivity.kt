package com.rodrigolmti.navigation.sample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.rodrigolmti.navigation.android.binding.viewBinding
import com.rodrigolmti.navigation.sample.R
import com.rodrigolmti.navigation.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding { ActivityMainBinding.inflate(layoutInflater) }

    private val navController by lazy { findNavController(R.id.fragNavHost) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        navController.apply {
            setupActionBarWithNavController(this)
        }
    }
}