package com.example.myrecipe2.repository

import android.util.Log
import com.example.myrecipe2.api.ApiService
import com.example.myrecipe2.api.responsemodel.RecipeListResponse
import com.example.myrecipe2.api.responsemodel.RecipeResponse
import com.example.myrecipe2.model.Recipe
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val apiService: ApiService
) :RecipeRepositoryInterface {

    suspend fun getRandomRecipes(): List<Recipe> {

        val result =
            apiService.getRandomRecipes().body()?.recipes?.map { mapToRecipeModel(it) } ?: listOf(
                Recipe()
            )

        return result
    }

    //Question: Right now, RecipeListResponse.RecipeResponse is required as the parameter type to mapToRecipeModel
    //How do I have it just RecipeResponse?

//    suspend fun getFilteredRecipes(): List<Recipe> {
//        return apiService.getFilteredRecipes().body()?.map{mapToRecipeModel(it)} ?: listOf(Recipe())
//    }

    fun mapToRecipeModel(response: RecipeResponse): Recipe {

        return Recipe(
            id = response.id,
            title = response.title,
            imageUrl = response.imageUrl,
            summary = response.summary
        )
    }
}
