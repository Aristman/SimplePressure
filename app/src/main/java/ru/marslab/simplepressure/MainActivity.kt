package ru.marslab.simplepressure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.simplepressure.ui.mainscreen.MainScreenView
import ru.marslab.simplepressure.ui.theme.SimplePressureTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimplePressureTheme {
                MainScreenView()
            }
        }
    }
}
