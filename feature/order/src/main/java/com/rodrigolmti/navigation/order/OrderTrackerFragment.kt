package com.rodrigolmti.navigation.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment

class OrderTrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = setContent {
        BuildOrderTrackerScreen()
    }

    @Preview
    @Composable
    fun BuildOrderTrackerScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally,
        ) {
            Text("Hello from Compose")
        }
    }
}