package co.happydevelopers.githubprojects

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RepositoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, RepositoriesActivity::class.java)
        }
    }
}
