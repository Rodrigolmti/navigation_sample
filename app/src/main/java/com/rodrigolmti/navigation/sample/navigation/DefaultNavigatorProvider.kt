package com.rodrigolmti.navigation.sample.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.rodrigolmti.navigation.sample.R

interface INavigatorProvider {
    val controller: NavController
}

class DefaultNavigatorProvider(private val activity: AppCompatActivity) : INavigatorProvider {
    override val controller by lazy {
        activity.findNavController(R.id.fragNavHost)
    }
}