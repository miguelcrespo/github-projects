package co.happydevelopers.githubprojects.utils

import co.happydevelopers.githubprojects.data.Database
import co.happydevelopers.githubprojects.data.UserRepository
import co.happydevelopers.githubprojects.ui.login.LoginViewModelFactory

object InjectorUtils {
    fun provideLoginViewFactory(): LoginViewModelFactory {
        val userRepository = UserRepository.getInstance(
            Database.getInstance().userDao
        )

        return LoginViewModelFactory(userRepository)
    }
}