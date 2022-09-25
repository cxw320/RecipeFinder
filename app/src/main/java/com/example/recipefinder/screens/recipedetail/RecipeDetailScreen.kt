package com.example.recipefinder.screens.recipedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipefinder.model.Recipe


data class RecipeDetailScreenState(
    val recipe : Recipe? = Recipe()
)

@Composable
fun RecipeDetailScreen(
    recipeDetailScreenState: RecipeDetailScreenState
){
    Scaffold(){
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            RecipeHeader(recipeDetailScreenState.recipe)
            RecipeImage(recipeDetailScreenState.recipe)
        }
    }
}

@Composable
fun RecipeHeader(
    recipe: Recipe?
){
    if (recipe != null) {
        Text(
            text = recipe.title
        )
    }
}

@Composable
fun RecipeImage(
    recipe: Recipe?
){
    AsyncImage(
        modifier = Modifier
            .size(300.dp),
        model = recipe?.imageUrl,
        contentDescription = "Recipe Image"
    )
}
