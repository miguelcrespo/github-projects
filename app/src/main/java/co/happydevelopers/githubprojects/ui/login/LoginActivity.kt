package co.happydevelopers.githubprojects.ui.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.happydevelopers.githubprojects.R
import co.happydevelopers.githubprojects.ui.repositories.RepositoriesActivity
import co.happydevelopers.githubprojects.utils.InjectorUtils
import co.happydevelopers.githubprojects.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.HttpUrl


class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = InjectorUtils.provideLoginViewFactory()
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)

        setContentView(R.layout.activity_login)

        initializeUI()
    }

    private fun initializeUI() {
        button_splash_github.setOnClickListener {
            val url = HttpUrl.Builder()
                .scheme("https")
                .host("github.com")
                .addPathSegments("login/oauth/authorize")
                .addQueryParameter("client_id", getString(R.string.github_client_id))
                .addQueryParameter("redirect_uri", "happydev://callback")
                .addQueryParameter("scope", "repo,read:user,user:email").build().url()

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val uri = intent.data
        if (uri != null && uri.toString().startsWith("happydev")) {
            val code = uri.getQueryParameter("code")

            if (code == null) {
                return
            }

            viewModel.getAccessToken(getString(R.string.github_client_id), getString(R.string.github_secret), code)
                .observe(this, Observer { githubToken ->
                    if (githubToken != null) {
                        Log.d("LoginActivity", "Token: ${githubToken.accessToken}")
                        NetworkUtils.changeBaseUrl("https://api.github.com")
                        NetworkUtils.GITHUB_TOKEN = githubToken.accessToken

                        viewModel.getAuthenticatedUser().observe(this, Observer {
                            if (it != null) {
                                Toast.makeText(this, "Welcome ${it.name}", Toast.LENGTH_LONG).show()
                                startActivity(RepositoriesActivity.getStartIntent(this))
                            }
                        })
                    }
                })
        }
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
