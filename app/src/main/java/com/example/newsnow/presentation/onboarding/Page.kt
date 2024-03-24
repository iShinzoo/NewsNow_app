package com.example.newsnow.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsnow.R


data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val page = listOf(
    Page(
        title = "Global Leaders Reach Climate Accord at Summit",
        description = "Stay informed on the latest environmental developments as world leaders unite to address pressing climate issues, promising significant policy changes and collaborative efforts towards sustainability.",
        image = R.drawable.onboard1
    ), Page(
        title = "Tech Giants Unveil Revolutionary AI-Powered Healthcare Solutions",
        description = "Explore groundbreaking advancements in healthcare technology as leading companies reveal innovative AI-driven solutions aimed at revolutionizing patient care, diagnosis, and treatment methods.",
        image = R.drawable.onboard2
    ), Page(
        title = "Renowned Author Shares Insights on New Bestseller",
        description = "Dive into the world of literature with an exclusive interview featuring a celebrated author discussing the inspiration behind their latest bestselling novel, offering readers unique perspectives and behind-the-scenes anecdotes.",
        image = R.drawable.onboard3
    )
)

