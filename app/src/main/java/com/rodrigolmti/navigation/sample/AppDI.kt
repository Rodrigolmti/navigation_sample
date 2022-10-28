package com.rodrigolmti.navigation.sample

import androidx.appcompat.app.AppCompatActivity
import com.rodrigolmti.navigation.checkout.ICheckoutDelegate
import com.rodrigolmti.navigation.restaurant.IRestaurantCatalogDelegate
import com.rodrigolmti.navigation.sample.delegates.checkout.CheckoutRouter
import com.rodrigolmti.navigation.sample.delegates.restaurant.catalog.RestaurantCatalogRouter
import org.koin.dsl.module

val routerModule = module {
    factory<IRestaurantCatalogDelegate> { (activity: AppCompatActivity) ->
        RestaurantCatalogRouter(activity)
    }
    factory<ICheckoutDelegate> { (activity: AppCompatActivity) ->
        CheckoutRouter(activity)
    }
}

val appModule = module {
    includes(listOf(routerModule))
}