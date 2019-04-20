package co.happydevelopers.githubprojects.utils

import co.happydevelopers.githubprojects.data.GithubToken
import co.happydevelopers.githubprojects.data.Repository
import co.happydevelopers.githubprojects.data.User
import retrofit2.Call
import retrofit2.http.*

interface JsonApi {

    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    fun getToken(@Field("client_id") clientId: String, @Field("client_secret") clientSecret: String, @Field("code") code: String): Call<GithubToken>

    @GET("user")
    @Headers("Accept: application/json")
    fun getAuthenticatedUser(@Header("Authorization") token: String): Call<User>

    @GET("user/repos")
    @Headers("Accept: application/json")
    fun getRepositories(@Header("Authorization") token: String): Call<List<Repository>>
}