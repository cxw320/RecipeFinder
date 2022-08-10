package com.example.myrecipe2.recipediscovery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipe2.model.Recipe
import com.example.myrecipe2.repository.RecipeRepository
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
}