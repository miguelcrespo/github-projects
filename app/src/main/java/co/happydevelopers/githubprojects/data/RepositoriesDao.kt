package co.happydevelopers.githubprojects.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RepositoriesDao {
    private val repositoriesList = mutableListOf<Repository>()
    private val repositories = MutableLiveData<List<Repository>>()


    init {
        repositories.value = repositoriesList
    }

    fun setRepositories(repositories: List<Repository>) {
        repositoriesList.clear()

        repositories.forEach {
            repositoriesList.add(it)
        }

        this.repositories.value = repositories
    }

    fun getRepositories() = repositories as LiveData<List<Repository>>
}