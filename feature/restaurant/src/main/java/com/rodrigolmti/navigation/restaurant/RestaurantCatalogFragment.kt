package com.rodrigolmti.navigation.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rodrigolmti.navigation.android.binding.viewBinding
import com.rodrigolmti.navigation.restaurant.databinding.FragmentRestaurantCatalogBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

data class RestaurantDTO(val items: Int)

interface IRestaurantCatalogDelegate {
    fun checkoutClick(dto: RestaurantDTO)
    fun detailClick()
}

class RestaurantCatalogFragment : Fragment() {

    private val binding by viewBinding { FragmentRestaurantCatalogBinding.inflate(layoutInflater) }
    private val delegate by inject<IRestaurantCatalogDelegate> { parametersOf(activity) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonCheckout.setOnClickListener { delegate.checkoutClick(RestaurantDTO(10)) }
            buttonDetail.setOnClickListener { delegate.detailClick() }
        }
    }
}