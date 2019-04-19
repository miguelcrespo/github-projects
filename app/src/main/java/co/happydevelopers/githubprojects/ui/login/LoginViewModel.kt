package co.happydevelopers.githubprojects.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.happydevelopers.githubprojects.data.GithubToken
import co.happydevelopers.githubprojects.data.User
import co.happydevelopers.githubprojects.data.UserRepository
import co.happydevelopers.githubprojects.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUser() = userRepository.getUser()

    fun setUser(user: User?) = userRepository.setUser(user)

    fun getAccessToken(clientId: String, clientSecret: String, code: String): LiveData<GithubToken?> {
        NetworkUtils.getJsonApi().getToken(clientId, clientSecret, code).enqueue(object : Callback<GithubToken> {
            override fun onFailure(call: Call<GithubToken>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<GithubToken>, response: Response<GithubToken>) {
                val githubToken = response.body()

                if (!response.isSuccessful || githubToken == null) {
                    return
                }

                userRepository.setGithubToken(githubToken)
            }
        })

        return userRepository.getGithubToken()
    }
}