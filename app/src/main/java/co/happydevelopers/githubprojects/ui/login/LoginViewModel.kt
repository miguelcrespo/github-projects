package co.happydevelopers.githubprojects.ui.login

import androidx.lifecycle.ViewModel
import co.happydevelopers.githubprojects.data.User
import co.happydevelopers.githubprojects.data.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUser() = userRepository.getUser()

    fun setUser(user: User?) = userRepository.setUser(user)
}