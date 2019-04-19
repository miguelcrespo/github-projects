package co.happydevelopers.githubprojects

import android.app.Application
import co.happydevelopers.githubprojects.data.Database

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Database.getInstance()
    }
}