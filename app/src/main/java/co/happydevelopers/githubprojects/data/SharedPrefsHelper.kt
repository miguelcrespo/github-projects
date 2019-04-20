package co.happydevelopers.githubprojects.data

import android.content.Context
import com.google.gson.Gson

class SharedPrefsHelper(val context: Context) {
    private val MY_PREFS = "MY_PREFS"
    private val USER_GITHUB_TOKEN = "USER_GITHUB_TOKEN"
    private val USER = "USER"
    private val mSharedPrefs = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE)

    fun setGithubToken(githubToken: GithubToken?) {
        val gson = Gson()

        mSharedPrefs.edit().putString(USER_GITHUB_TOKEN, gson.toJson(githubToken)).apply()
    }

    fun getGithubToken(): GithubToken? {
        val userString = mSharedPrefs.getString(USER_GITHUB_TOKEN, null)
        val gson = Gson()

        return if (userString == null) {
            null
        } else {
            gson.fromJson(userString, GithubToken::class.java)
        }
    }

    fun setUser(user: User?) {
        val gson = Gson()

        mSharedPrefs.edit().putString(USER, gson.toJson(user)).apply()
    }

    fun getUser(): User? {
        val userString = mSharedPrefs.getString(USER, null)
        val gson = Gson()

        return if (userString == null) {
            null
        } else {
            gson.fromJson(userString, User::class.java)
        }
    }
}