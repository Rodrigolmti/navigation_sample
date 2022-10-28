package com.rodrigolmti.navigation.sample.mapper

import com.rodrigolmti.navigation.checkout.CheckoutArgs
import com.rodrigolmti.navigation.restaurant.RestaurantDTO

fun RestaurantDTO.toArgs() = CheckoutArgs(items)