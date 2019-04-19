package co.happydevelopers.githubprojects.data

class UserRepository private constructor(private val userDao: UserDao) {
    fun getUser() = userDao.getUser()

    fun setUser(user: User?) = userDao.setUser(user)

    fun setGithubToken(githubToken: GithubToken) {
        userDao.setToken(githubToken)
    }

    fun getGithubToken() = userDao.getTokenr()

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also {
                    instance = it
                }
            }
    }
}