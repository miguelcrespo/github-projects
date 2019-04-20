package co.happydevelopers.githubprojects.utils

import co.happydevelopers.githubprojects.data.Database
import co.happydevelopers.githubprojects.data.RepositoriesRepository
import co.happydevelopers.githubprojects.data.UserRepository
import co.happydevelopers.githubprojects.ui.login.LoginViewModelFactory
import co.happydevelopers.githubprojects.ui.repositories.RepositoriesViewModelFactory

object InjectorUtils {
    fun provideLoginViewFactory(): LoginViewModelFactory {
        val userRepository = UserRepository.getInstance(
            Database.getInstance().userDao,
            Database.getInstance().sharedPrefsHelper
        )

        return LoginViewModelFactory(userRepository)
    }

    fun provideRepositoresViewFactory(): RepositoriesViewModelFactory {
        val userRepository = UserRepository.getInstance(
            Database.getInstance().userDao,
            Database.getInstance().sharedPrefsHelper
        )

        val repositoriesRepository =
            RepositoriesRepository.getInstance(Database.getInstance().userDao, Database.getInstance().repositoriesDao)

        return RepositoriesViewModelFactory(userRepository, repositoriesRepository)
    }
}