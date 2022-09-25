package com.example.recipefinder.screens.recipediscovery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.model.Recipe
import com.example.recipefinder.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDiscoveryViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private var recipeList = emptyList<Recipe>()

    val recipeDiscoveryScreenStateFlow = MutableStateFlow(RecipeDiscoveryScreenState())

    init{
        viewModelScope.launch{
            recipeList = recipeRepository.getRandomRecipes()
            recipeDiscoveryScreenStateFlow.emit(
                recipeDiscoveryScreenStateFlow.value.copy(
                    recipeList = recipeList
                )
            )
            Log.d("Caroline","recipe list: ${recipeList}")
        }
    }

    fun updateSearchBarText(query: String){
        Log.d("Caroline","update search bar is called $query")
        viewModelScope.launch{
            recipeDiscoveryScreenStateFlow.emit(
                recipeDiscoveryScreenStateFlow.value.copy(
                    recipeList = recipeList,
                    searchQueryState = query
                )
            )
        }
        Log.d("Caroline","Screen state flow is: ${recipeDiscoveryScreenStateFlow.value.searchQueryState}")
    }

    fun searchForRecipe(searchParameters:String){
        viewModelScope.launch{
            recipeList = recipeRepository.searchRecipes(searchParameters)
            recipeDiscoveryScreenStateFlow.emit(
                recipeDiscoveryScreenStateFlow.value.copy(
                    recipeList = recipeList
                )
            )
        }

    }
}