package com.rodrigolmti.navigation.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rodrigolmti.navigation.android.binding.viewBinding
import com.rodrigolmti.navigation.restaurant.databinding.FragmentRestaurantDetailBinding

class RestaurantDetailFragment : Fragment() {

    private val binding by viewBinding { FragmentRestaurantDetailBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root
}