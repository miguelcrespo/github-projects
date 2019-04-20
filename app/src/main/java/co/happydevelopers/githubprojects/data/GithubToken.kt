package co.happydevelopers.githubprojects.data

import com.google.gson.annotations.SerializedName


data class GithubToken(
    @SerializedName("access_token") val accessToken: String, val scope: String,
    @SerializedName("token_type") val tokenType: String
)