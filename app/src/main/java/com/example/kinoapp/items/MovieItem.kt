package com.example.kinoapp.items

data class MovieItem (
//    val films: List<Films>,
//) {
//    data class Films(
        val description: String,
        val id: Int,
        val url: String,
        val title: String,
        val premiere: Boolean,
        val showing: List<Showing>,
    ) {
        data class Showing(
            val blocked: List<String>,
            val time: String,
        )
    }
//}
