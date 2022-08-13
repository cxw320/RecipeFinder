package com.example.myrecipe2.api.responsemodel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter=true)
data class RecipeListResponse(
    @Json(name="recipes") val recipes: List<RecipeResponse>
)