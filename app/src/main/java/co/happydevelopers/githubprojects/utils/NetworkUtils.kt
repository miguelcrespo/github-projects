package co.happydevelopers.githubprojects.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {
    private const val BASE_URL = "https://github.com"
    var GITHUB_TOKEN: String? = null

    private var retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun getJsonApi(): JsonApi {
        return retrofit.create(JsonApi::class.java)
    }

    fun changeBaseUrl(baseUrl: String) {
        retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}