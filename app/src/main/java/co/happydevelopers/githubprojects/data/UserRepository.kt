package co.happydevelopers.githubprojects.data

import androidx.lifecycle.LiveData
import co.happydevelopers.githubprojects.utils.NetworkUtils

class UserRepository private constructor(
    private val userDao: UserDao,
    private val sharedPrefsHelper: SharedPrefsHelper
) {

    fun getUser(): LiveData<User?> {
        return userDao.getUser()
    }

    fun setUser(user: User?) = userDao.setUser(user)

    fun setGithubToken(githubToken: GithubToken) {
        sharedPrefsHelper.setGithubToken(githubToken)
        userDao.setToken(githubToken)
    }

    fun getGithubToken() = userDao.getTokenr()

    fun storeUser(user: User) {
        sharedPrefsHelper.setUser(user)
    }

    fun getStoreUser(): User? {
        return sharedPrefsHelper.getUser()
    }

    fun getStoreToken(): GithubToken? {
        return sharedPrefsHelper.getGithubToken()
    }

    fun logout() {
        sharedPrefsHelper.setUser(null)
        sharedPrefsHelper.setGithubToken(null)
        userDao.setUser(null)
        NetworkUtils.GITHUB_TOKEN = null
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao, sharedPrefsHelper: SharedPrefsHelper) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao, sharedPrefsHelper).also {
                    instance = it
                }
            }
    }
}