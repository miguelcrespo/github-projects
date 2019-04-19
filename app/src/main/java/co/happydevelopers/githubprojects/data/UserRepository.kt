package co.happydevelopers.githubprojects.data

class UserRepository private constructor(private val userDao: UserDao) {
    fun getUser() = userDao.getUser()

    fun setUser(user: User?) = userDao.setUser(user)

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