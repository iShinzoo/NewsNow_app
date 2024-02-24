package com.example.newsnow.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsnow.R


data class Page (
    val title : String,
    val description : String,
    @DrawableRes val image : Int
    )

val page = listOf(
    Page (
        title = "This is News App",
        description = "This is a some random description for the News App i am building in public",
        image = R.drawable.image1
    ),
    Page (
        title = "This is News App",
        description = "This is a some random description for the News App i am building in public",
        image = R.drawable.image2
    ),
    Page (
        title = "This is News App",
        description = "This is a some random description for the News App i am building in public",
        image = R.drawable.image3
    )
)

