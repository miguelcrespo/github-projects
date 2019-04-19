package co.happydevelopers.githubprojects.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserDao {
    private val user = MutableLiveData<User?>().apply { postValue(null) }
    private val githubToken = MutableLiveData<GithubToken?>().apply { postValue(null) }

    fun setUser(user: User?) {
        this.user.value = user
    }

    fun getUser() = user as LiveData<User?>

    fun setToken(githubToken: GithubToken?) {
        this.githubToken.value = githubToken
    }

    fun getTokenr() = githubToken as LiveData<GithubToken?>
}