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
                val newGithubToken = response.body()

                if (!response.isSuccessful || newGithubToken == null) {
                    return
                }

                NetworkUtils.GITHUB_TOKEN = newGithubToken.accessToken
                userRepository.setGithubToken(newGithubToken)
            }
        })

        return userRepository.getGithubToken()
    }

    fun getGithubToken(): LiveData<GithubToken?> {
        return userRepository.getGithubToken()
    }

    fun setGithubToken(githubToken: GithubToken) {
        return userRepository.setGithubToken(githubToken)
    }

    fun getAuthenticatedUser(): LiveData<User?> {
        NetworkUtils.getJsonApi()
            .getAuthenticatedUser("bearer ${NetworkUtils.GITHUB_TOKEN}")
            .enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {

                        val user = response.body()

                        if (user == null) return

                        userRepository.setUser(user)
                        userRepository.storeUser(user)
                    }
                }
            })

        return userRepository.getUser()
    }

    fun getStoreUser(): User? {
        return userRepository.getStoreUser()
    }

    fun getStoreToken(): GithubToken? {
        return userRepository.getStoreToken()
    }

    fun logout() {
        userRepository.logout()
    }
}