package com.example.myrecipe2.api

import com.example.myrecipe2.api.responsemodel.RecipeListResponse
import com.example.myrecipe2.api.responsemodel.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("findByIngredients?apiKey=bcaa49c492c948dc99175ac11e8dcb66&ingredients=apples")
    suspend fun getFilteredRecipes(): Response<List<RecipeResponse>>

    @GET("random?apiKey=bcaa49c492c948dc99175ac11e8dcb66&number=20")
    suspend fun getRandomRecipes(): Response<RecipeListResponse>
}