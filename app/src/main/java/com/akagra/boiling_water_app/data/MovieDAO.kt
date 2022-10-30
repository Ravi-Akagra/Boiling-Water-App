package com.akagra.boiling_water_app.data

import android.content.Context
import com.akagra.boiling_water_app.credentials.Credentials
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class MovieDAO {
    private val credentials: Credentials = Credentials()  // Added credentials
    private val baseUrl = credentials.movieApi          // MAKE a credentials package and a Credentials class (com.akagra.boiling_water_app.credentials.Credentials)
    private val passcode = credentials.apiPasscode      // ADD these 2 val in class of type String

    fun getPage(context:Context, page:Int, callback: VolleyCallback) {

        val url = "$baseUrl?passcode=$passcode&pageNo=$page"
        println(url)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
            {
            val moviesJsonArray = it.getJSONArray("results")
            val moviesObjectArray = ArrayList<Movie>()

            for (i in 0 until moviesJsonArray.length()){
                val movieJson = moviesJsonArray.getJSONObject(i)

                val movie = Movie(
                    id=movieJson.getInt("id"),
                    poster=movieJson.optString("poster"),
                    title=movieJson.getString("title"),
                    release_date=movieJson.getString("release_date"),
                    runtime=movieJson.getString("runtime"),
                    bw_rating=movieJson.getString("BW_rating"),
                    more_data=movieJson.getJSONObject("more_data")
                )
                moviesObjectArray.add(movie)
                println(movie.title)
            }
            callback.onSuccessResponse(moviesObjectArray) },

            {
                println("some error occored ${it.toString()}")
            }
        )
        VolleySingletonClass.getInstance(context).addToRequestQueue(jsonObjectRequest)
}

    fun getPageCount(): Int {
        return 14
    }
}

interface VolleyCallback {
    fun onSuccessResponse(result: ArrayList<Movie>)
}
