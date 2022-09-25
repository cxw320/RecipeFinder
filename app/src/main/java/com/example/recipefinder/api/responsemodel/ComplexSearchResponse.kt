package com.example.recipefinder.api.responsemodel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter=true)
data class ComplexSearchResponse(
    @Json(name="results") val result: List<RecipeResponse>
)