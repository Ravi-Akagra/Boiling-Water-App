package com.akagra.boiling_water_app.data

import org.json.JSONObject

data class Movie(
    val id:Int,
    val poster: String?,
    val title:String,
    val release_date:String?,
    val runtime: String?,
    val bw_rating: String,
    val more_data: JSONObject,
)
