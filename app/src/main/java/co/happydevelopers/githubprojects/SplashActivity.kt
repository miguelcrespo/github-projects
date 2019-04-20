package co.happydevelopers.githubprojects

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import co.happydevelopers.githubprojects.data.User
import co.happydevelopers.githubprojects.ui.login.LoginActivity
import co.happydevelopers.githubprojects.ui.login.LoginViewModel
import co.happydevelopers.githubprojects.ui.repositories.RepositoriesActivity
import co.happydevelopers.githubprojects.utils.InjectorUtils
import co.happydevelopers.githubprojects.utils.NetworkUtils

class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val factory = InjectorUtils.provideLoginViewFactory()
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)

        initializeUI()
    }

    private fun initializeUI() {
        //viewModel.logout()
        user = viewModel.getStoreUser()

        if (user != null) {
            viewModel.setUser(user)
            val token = viewModel.getStoreToken()

            if (token != null) {
                NetworkUtils.GITHUB_TOKEN = token.accessToken
                NetworkUtils.changeBaseUrl("https://api.github.com")

                startActivity(RepositoriesActivity.getStartIntent(this))
            } else {
                startActivity(LoginActivity.getStartIntent(this))
            }

        } else {
            startActivity(LoginActivity.getStartIntent(this))
        }
    }
}
