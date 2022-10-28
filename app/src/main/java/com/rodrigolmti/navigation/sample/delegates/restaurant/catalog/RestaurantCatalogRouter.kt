package com.rodrigolmti.navigation.sample.delegates.restaurant.catalog

import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.rodrigolmti.navigation.restaurant.IRestaurantCatalogDelegate
import com.rodrigolmti.navigation.restaurant.RestaurantDTO
import com.rodrigolmti.navigation.sample.mapper.toArgs
import com.rodrigolmti.navigation.sample.navigation.DefaultNavigatorProvider
import com.rodrigolmti.navigation.sample.navigation.INavigatorProvider
import com.rodrigolmti.navigation.checkout.R as checkout
import com.rodrigolmti.navigation.restaurant.R as restaurant

class RestaurantCatalogRouter(private val activity: AppCompatActivity) :
    INavigatorProvider by DefaultNavigatorProvider(
        activity
    ), IRestaurantCatalogDelegate {

    override fun checkoutClick(dto: RestaurantDTO) {
        val args = dto.toArgs()
        controller.navigate(checkout.id.navigation_checkout, bundleOf("args" to args))
    }

    override fun detailClick() {
        controller.navigate(restaurant.id.restaurantDetailFragment)
    }
}
