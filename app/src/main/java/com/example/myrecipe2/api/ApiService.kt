package com.example.myrecipe2.api

import com.example.myrecipe2.api.responsemodel.ComplexSearchResponse
import com.example.myrecipe2.api.responsemodel.RecipeListResponse
import com.example.myrecipe2.api.responsemodel.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("findByIngredients?apiKey=bcaa49c492c948dc99175ac11e8dcb66&ingredients=apples")
    suspend fun getFilteredRecipes(): Response<List<RecipeResponse>>

    @GET("random?apiKey=bcaa49c492c948dc99175ac11e8dcb66&number=20")
    suspend fun getRandomRecipes(): Response<RecipeListResponse>

    @GET("complexSearch?apiKey=bcaa49c492c948dc99175ac11e8dcb66&number=20")
    suspend fun searchRecipes(
        @Query("query") searchParameters : String
    ): Response<ComplexSearchResponse>

    @GET("recipes/{recipeId}/information?apiKey=bcaa49c492c948dc99175ac11e8dcb66")
    suspend fun searchIndividualRecipe(
        @Path("recipeId") recipeId: String
    ): Response<RecipeResponse>
}