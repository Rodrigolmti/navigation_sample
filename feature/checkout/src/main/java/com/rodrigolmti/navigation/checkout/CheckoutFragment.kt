package com.rodrigolmti.navigation.checkout

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.rodrigolmti.navigation.android.binding.viewBinding
import com.rodrigolmti.navigation.checkout.databinding.FragmentCheckoutBinding
import kotlinx.parcelize.Parcelize
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

@Parcelize
data class CheckoutArgs(val items: Int) : Parcelable

interface ICheckoutDelegate {
    fun paymentSuccess()
}

class CheckoutFragment : Fragment() {

    private val binding by viewBinding { FragmentCheckoutBinding.inflate(layoutInflater) }
    private val delegate by inject<ICheckoutDelegate> { parametersOf(activity) }
    private val args: CheckoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            textView.text = "Checkout items: ${args.args.items}"
            buttonPayment.setOnClickListener {
                delegate.paymentSuccess()
            }
        }
    }
}