package com.example.myrecipe2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myrecipe2.recipediscovery.RecipeDiscoveryScreen
import com.example.myrecipe2.recipediscovery.RecipeDiscoveryViewModel
import com.example.myrecipe2.ui.theme.MyRecipe2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val recipeDiscoveryViewModel: RecipeDiscoveryViewModel by viewModels()

            MyRecipe2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val recipeDiscoveryScreenState = recipeDiscoveryViewModel
                        .recipeDiscoveryScreenStateFlow.collectAsState()
                    RecipeDiscoveryScreen(
                        recipeDiscoveryScreenState = recipeDiscoveryScreenState.value
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
