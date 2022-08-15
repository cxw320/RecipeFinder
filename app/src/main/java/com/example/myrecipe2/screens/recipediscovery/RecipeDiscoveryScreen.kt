package com.example.myrecipe2.screens.recipediscovery

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myrecipe2.model.Recipe
import com.example.myrecipe2.ui.theme.OffWhite


data class RecipeDiscoveryScreenState(
    val recipeList: List<Recipe> = listOf(),
    val searchQueryState: String = ""
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeDiscoveryScreen(
    recipeDiscoveryScreenState: RecipeDiscoveryScreenState,
    updateSearchBarText: (name: String) -> Unit,
    searchRecipes: (searchParameters: String) -> Unit,
    onRecipeClick: (recipeId: String) -> Unit
) {

    Log.d(
        "Caroline",
        "recipeDiscoveryScreenState value is ${recipeDiscoveryScreenState.recipeList}"
    )
    Column {
        RecipeDiscoverySearchHeader(
            recipeDiscoveryScreenState = recipeDiscoveryScreenState,
            updateSearchBarText = updateSearchBarText,
            searchRecipes = searchRecipes
        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(5.dp),
            cells = GridCells.Fixed(2)
        ) {
            items(recipeDiscoveryScreenState.recipeList) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onRecipeClick = onRecipeClick
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecipeDiscoverySearchHeader(
    recipeDiscoveryScreenState: RecipeDiscoveryScreenState,
    updateSearchBarText: (name: String) -> Unit,
    searchRecipes: (searchParameters: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(BorderStroke(1.dp, androidx.compose.ui.graphics.Color.Black)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            value = recipeDiscoveryScreenState.searchQueryState,
            onValueChange = { value ->
                updateSearchBarText(value)
                Log.d(
                    "Caroline",
                    "Composable is called again with ${recipeDiscoveryScreenState.recipeList}"
                )
            },
            placeholder = { Text("Dish name, Ingredients, cuisine type etc...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
                .border(BorderStroke(1.dp, MaterialTheme.colors.onPrimary)),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .size(24.dp)
                )
            },
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = {
                Log.d("Caroline","done")
                searchRecipes(recipeDiscoveryScreenState.searchQueryState)
                keyboardController?.hide()
            }),
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onPrimary,
                cursorColor = MaterialTheme.colors.onSurface,
                leadingIconColor = MaterialTheme.colors.onPrimary,
                trailingIconColor = MaterialTheme.colors.onSurface,
                focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                unfocusedIndicatorColor = MaterialTheme.colors.onPrimary,
                disabledIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
            )
        )

    }
}

@Composable
fun RecipeCard(
    recipe: Recipe,
    onRecipeClick: (recipeId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .height(230.dp)
            .padding(5.dp)
            .clickable{
                onRecipeClick
            }
    ) {
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
