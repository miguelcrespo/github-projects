package co.happydevelopers.githubprojects.ui.repositories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.happydevelopers.githubprojects.R
import co.happydevelopers.githubprojects.data.Repository
import co.happydevelopers.githubprojects.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_repositories.*


class RepositoriesActivity : AppCompatActivity() {
    private lateinit var viewModel: RepositoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        val factory = InjectorUtils.provideRepositoresViewFactory()
        viewModel = ViewModelProviders.of(this, factory).get(RepositoriesViewModel::class.java)

        setSupportActionBar(toolbar_repositories_toolbar as Toolbar?)

        initializeUI()
    }

    private fun initializeUI() {
        val viewManager = LinearLayoutManager(this)
        var viewAdapter = RepositoriesAdapter(listOf())

        recyclerView_repositories_repositories.apply {
            layoutManager = viewManager
            adapter = viewAdapter
            setHasFixedSize(true)
        }

        viewModel.getRepositories().observe(this, Observer { repositories2 ->
            if (repositories2 != null) {
                viewAdapter.updateData(repositories2)
            }
        })

        viewModel.loadRepositories()
    }

    companion object {
        val TAG = RepositoriesActivity::class.java.simpleName

        fun getStartIntent(context: Context): Intent {
            return Intent(context, RepositoriesActivity::class.java)
        }
    }


}

class RepositoriesAdapter(private var dataSet: List<Repository>) :
    RecyclerView.Adapter<RepositoriesAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    fun updateData(repositories: List<Repository>) {
        dataSet = repositories

        notifyDataSetChanged()
    }
}