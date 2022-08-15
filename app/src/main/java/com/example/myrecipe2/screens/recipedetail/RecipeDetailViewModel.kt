package com.example.myrecipe2.screens.recipedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipe2.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    val recipeDetailScreenStateFlow = MutableStateFlow(RecipeDetailScreenState())

    fun getRecipe(recipeId: String?) {
        viewModelScope.launch {
            recipeDetailScreenStateFlow.emit(
                recipeDetailScreenStateFlow.value.copy(
                    recipe = recipeId?.let { recipeRepository.getIndividualRecipe(it) }
                )
            )
        }

    }

}