package com.erikriosetiawan.cinemamovies

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_movie, R.id.navigation_tv_show, R.id.navigation_favorite
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchMovie: MenuItem? = menu?.findItem(R.id.item_search_movie)
        val searchViewMovie: SearchView = searchMovie?.actionView as SearchView
        searchViewMovie.queryHint = resources.getString(R.string.search_movie)
        searchViewMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val sendQueryIntent = Intent(this@MainActivity, SearchActivity::class.java)
                sendQueryIntent.putExtra(
                    SearchActivity.SEARCH_KEY,
                    SearchActivity.SEARCH_MOVIE_KEY
                )
                sendQueryIntent.putExtra(SearchActivity.SEARCH_MOVIE_KEY, query)
                startActivity(sendQueryIntent)
                Log.d("TES123", "Query berada di Movie")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        val searchTvShow: MenuItem? = menu?.findItem(R.id.item_search_tv_show)
        val searchViewTvShow: SearchView = searchTvShow?.actionView as SearchView
        searchViewTvShow.queryHint = getString(R.string.search_tv_show)
        searchViewTvShow.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val sendQueryIntent = Intent(this@MainActivity, SearchActivity::class.java)
                sendQueryIntent.putExtra(
                    SearchActivity.SEARCH_KEY,
                    SearchActivity.SEARCH_TV_SHOW_KEY
                )
                sendQueryIntent.putExtra(SearchActivity.SEARCH_TV_SHOW_KEY, query)
                startActivity(sendQueryIntent)
                Log.d("TES123", "Query berada di Tv Show")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.action_change_language -> {
                    val changeLanguageIntent = Intent()
                    changeLanguageIntent.action = Settings.ACTION_LOCALE_SETTINGS
                    startActivity(changeLanguageIntent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
