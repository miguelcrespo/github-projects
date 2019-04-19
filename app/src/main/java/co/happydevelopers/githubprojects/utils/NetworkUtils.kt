package co.happydevelopers.githubprojects.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {
    private const val BASE_URL = "https://github.com"
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun getJsonApi(): JsonApi {
        return retrofit.create(JsonApi::class.java)
    }
}