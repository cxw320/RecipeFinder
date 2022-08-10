package com.example.myrecipe2.recipediscovery

import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myrecipe2.model.Recipe
import com.example.myrecipe2.ui.theme.OffWhite


data class RecipeDiscoveryScreenState(
    val recipeList: List<Recipe> = listOf(),
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeDiscoveryScreen(
    recipeDiscoveryScreenState: RecipeDiscoveryScreenState
){
    Column{
        RecipeDiscoverySearchHeader(
            recipeDiscoveryScreenState = RecipeDiscoveryScreenState()
        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(5.dp),
            cells = GridCells.Fixed(2)
        ){
            items(recipeDiscoveryScreenState.recipeList){ recipe ->
                RecipeCard(
                    recipe = recipe
                )
            }
        }
    }
}

@Composable
fun RecipeDiscoverySearchHeader(
    recipeDiscoveryScreenState : RecipeDiscoveryScreenState
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(BorderStroke(1.dp, androidx.compose.ui.graphics.Color.Black))
    ){
        Column(
            modifier = Modifier.width(350.dp)
        ){

        }
    }
}

@Composable
fun RecipeCard(
    recipe: Recipe
){
    Column(
        modifier = Modifier
            .height(230.dp)
            .padding(5.dp)
    ){
        AsyncImage(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            model = recipe.imageUrl,
            contentDescription = "test",
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = recipe.title,
            color = OffWhite,
            fontSize = 16.sp
        )
    }
}
