package co.happydevelopers.githubprojects.utils

import co.happydevelopers.githubprojects.data.GithubToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface JsonApi {

    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    fun getToken(@Field("client_id") clientId: String, @Field("client_secret") clientSecret: String, @Field("code") code: String): Call<GithubToken>
}