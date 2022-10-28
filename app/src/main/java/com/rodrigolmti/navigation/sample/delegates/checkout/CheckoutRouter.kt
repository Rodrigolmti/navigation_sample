package com.rodrigolmti.navigation.sample.delegates.checkout

import androidx.appcompat.app.AppCompatActivity
import com.rodrigolmti.navigation.checkout.ICheckoutDelegate
import com.rodrigolmti.navigation.order.R
import com.rodrigolmti.navigation.sample.navigation.DefaultNavigatorProvider
import com.rodrigolmti.navigation.sample.navigation.INavigatorProvider

class CheckoutRouter (private val activity: AppCompatActivity) :
    INavigatorProvider by DefaultNavigatorProvider(
        activity
    ), ICheckoutDelegate {

    override fun paymentSuccess() {
        controller.navigate(R.id.navigation_order)
    }
}