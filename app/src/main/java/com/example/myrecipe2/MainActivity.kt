package com.example.myrecipe2

import android.accounts.AccountManager.get
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myrecipe2.recipediscovery.RecipeDiscoveryScreen
import com.example.myrecipe2.recipediscovery.RecipeDiscoveryViewModel
import com.example.myrecipe2.ui.theme.MyRecipe2Theme
import dagger.hilt.EntryPoints.get
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Array.get

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val recipeDiscoveryViewModel : RecipeDiscoveryViewModel by viewModels()

            MyRecipe2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyRecipeNavHost(navController,recipeDiscoveryViewModel)
                }
            }
        }
    }
}

@Composable
fun MyRecipeNavHost(
    navController: NavHostController,
    recipeDiscoveryViewModel: RecipeDiscoveryViewModel
){
    NavHost(
        navController = navController,
        startDestination = MyRecipeScreens.RECIPE_DISCOVERY.name
    ){
        composable(MyRecipeScreens.RECIPE_DISCOVERY.name){

            val recipeDiscoveryScreenState = recipeDiscoveryViewModel
                .recipeDiscoveryScreenStateFlow.collectAsState()
            RecipeDiscoveryScreen(
                recipeDiscoveryScreenState = recipeDiscoveryScreenState.value,
                updateSearchBarText = recipeDiscoveryViewModel::updateSearchBarText
            )
        }
    }

}