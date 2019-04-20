package co.happydevelopers.githubprojects.data

class RepositoriesRepository private constructor(
    private val userDao: UserDao,
    private val repositoriesDao: RepositoriesDao
) {

    fun getRepositores() = repositoriesDao.getRepositories()

    fun setRepositories(repositories: List<Repository>) = repositoriesDao.setRepositories(repositories)


    companion object {
        @Volatile
        private var instance: RepositoriesRepository? = null

        fun getInstance(userDao: UserDao, repositoriesDao: RepositoriesDao) =
            instance ?: synchronized(this) {
                instance ?: RepositoriesRepository(userDao, repositoriesDao).also {
                    instance = it
                }
            }
    }
}