package com.example.ghostgame.ui.util

import com.example.ghostgame.R

private val ghostImageResources =
    listOf(
        R.drawable.ic_ghost_1,
        R.drawable.ic_ghost_2,
        R.drawable.ic_ghost_3,
        R.drawable.ic_ghost_4,
        R.drawable.ic_ghost_5
    )

fun getRandomGhostImage() = ghostImageResources.random()