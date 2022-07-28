package com.cricbuzzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cricbuzzapp.theme.CricbuzzAppTheme
import com.cricbuzzapp.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), MainNavigator {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setNavigator(this)
        setContent {
            CricbuzzAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainActivityCompose().SetupMainActivityCompose()
                }
            }
        }
    }

    override fun onSearchResultSuccess() {
        hideKeyboard()
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CricbuzzAppTheme {
        MainActivityCompose().SetupMainActivityCompose()
    }
}