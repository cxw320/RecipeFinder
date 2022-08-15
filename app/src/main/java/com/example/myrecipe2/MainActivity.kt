package com.example.myrecipe2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myrecipe2.screens.recipedetail.RecipeDetailScreen
import com.example.myrecipe2.screens.recipedetail.RecipeDetailViewModel
import com.example.myrecipe2.screens.recipediscovery.RecipeDiscoveryScreen
import com.example.myrecipe2.screens.recipediscovery.RecipeDiscoveryViewModel
import com.example.myrecipe2.ui.theme.MyRecipe2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val recipeDiscoveryViewModel : RecipeDiscoveryViewModel by viewModels()
            val recipeDetailViewModel : RecipeDetailViewModel by viewModels()

            MyRecipe2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyRecipeNavHost(navController,recipeDiscoveryViewModel,recipeDetailViewModel)
                }
            }
        }
    }
}

@Composable
fun MyRecipeNavHost(
    navController: NavHostController,
    recipeDiscoveryViewModel: RecipeDiscoveryViewModel,
    recipeDetailViewModel: RecipeDetailViewModel
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
                updateSearchBarText = recipeDiscoveryViewModel::updateSearchBarText,
                searchRecipes = recipeDiscoveryViewModel::searchForRecipe,
                onRecipeClick = { recipeId -> navController.navigate("${MyRecipeScreens.RECIPE_DETAIL.name}/${recipeId}")}
            )
        }
        composable(
            route = "${MyRecipeScreens.RECIPE_DETAIL.name}/{recipeId}",
            arguments = listOf(navArgument(name = "recipeId"){
                type = NavType.StringType
            })
        ){
            val recipeDetailScreenState = recipeDetailViewModel.recipeDetailScreenStateFlow.collectAsState()

            recipeDetailViewModel.getRecipe(it.arguments?.getString("recipeId"))
            RecipeDetailScreen(recipeDetailScreenState.value)
        }
    }

}