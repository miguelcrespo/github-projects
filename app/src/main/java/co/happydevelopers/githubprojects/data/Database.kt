package co.happydevelopers.githubprojects.data

class Database private constructor() {
    var userDao = UserDao()
        private set

    lateinit var sharedPrefsHelper: SharedPrefsHelper

    companion object {
        @Volatile
        private var instance: Database? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: Database().also {
                instance = it
            }
        }
    }
}
