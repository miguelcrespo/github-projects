package co.happydevelopers.githubprojects

import android.app.Application
import co.happydevelopers.githubprojects.data.Database
import co.happydevelopers.githubprojects.data.SharedPrefsHelper

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val db = Database.getInstance()
        db.sharedPrefsHelper = SharedPrefsHelper(this)
    }
}