package co.happydevelopers.githubprojects

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.HttpUrl


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                .addQueryParameter("scope", "repo").build().url()

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val uri = intent.data
        if (uri != null && uri.toString().startsWith("happydev")) {
            val code = uri.getQueryParameter("code")
            Toast.makeText(this, "Yes: $code", Toast.LENGTH_LONG).show()
        }
    }
}
