package com.example.indeetvapp.model

import com.google.gson.annotations.SerializedName

data class TestData(@SerializedName("TestData") val testData: List<PosterData>) {
}