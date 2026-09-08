package com.example.us06


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.us06.data.remote.RetrofitInstance
import com.example.us06.data.repository.ProductRepositoryImpl
import com.example.us06.presentation.addproduct.AddProductScreen
import com.example.us06.presentation.addproduct.AddProductViewModel
import com.example.us06.ui.theme.US06Theme


class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        val repository =
            ProductRepositoryImpl(
                RetrofitInstance.api
            )

        val viewModel =
            AddProductViewModel(
                repository
            )

        setContent {

            US06Theme {

                AddProductScreen(

                    viewModel = viewModel
                )
            }
        }
    }
}
