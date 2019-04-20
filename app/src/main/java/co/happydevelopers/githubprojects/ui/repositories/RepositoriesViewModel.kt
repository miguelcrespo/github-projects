package co.happydevelopers.githubprojects.ui.repositories

import androidx.lifecycle.ViewModel
import co.happydevelopers.githubprojects.data.RepositoriesRepository
import co.happydevelopers.githubprojects.data.Repository
import co.happydevelopers.githubprojects.data.UserRepository
import co.happydevelopers.githubprojects.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesViewModel(
    private val userRepository: UserRepository,
    private val repositoriesRepository: RepositoriesRepository
) : ViewModel() {
    fun loadRepositories() {
        NetworkUtils.getJsonApi().getRepositories("bearer ${NetworkUtils.GITHUB_TOKEN!!}")
            .enqueue(object : Callback<List<Repository>> {
                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                    if (!response.isSuccessful) {
                        return
                    }

                    var repositories = response.body()

                    if (repositories == null) repositories = listOf()

                    repositoriesRepository.setRepositories(repositories)
                }

            })
    }

    fun getRepositories() = repositoriesRepository.getRepositores()

}