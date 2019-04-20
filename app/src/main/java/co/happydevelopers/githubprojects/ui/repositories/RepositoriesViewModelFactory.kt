package co.happydevelopers.githubprojects.ui.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.happydevelopers.githubprojects.data.RepositoriesRepository
import co.happydevelopers.githubprojects.data.UserRepository

class RepositoriesViewModelFactory(
    private val userRepository: UserRepository,
    private val repositoriesRepository: RepositoriesRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepositoriesViewModel(userRepository, repositoriesRepository) as T
    }
}