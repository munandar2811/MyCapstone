package com.example.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.favorite.di.favoriteModul
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteActivity : AppCompatActivity(), OnClick {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()
    private var stateJob: Job?=null
    private lateinit var adapter:AdapterFavorite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = "Favorite Capstone"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        loadKoinModules(favoriteModul)


        lifecycleScope.launch {
            viewModel.onRefresh()
        }

        lifecycleScope.launch {
            viewModel.favorite.collect {

                adapter = AdapterFavorite(this@FavoriteActivity, it)
                with(binding) {
                    rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity,LinearLayoutManager.VERTICAL,false)
                    rvFavorite.setHasFixedSize(true)
                    rvFavorite.adapter = adapter
                }
            }
        }


    }

    override fun onResume() {

        super.onResume()
        lifecycleScope.launch {
            viewModel.onRefresh()
        }
        stateJob = lifecycleScope.launch {
            viewModel.favorite.collect {
                adapter = AdapterFavorite(this@FavoriteActivity,it)
                Log.e("onResume: ", it.toString())
            }
        }

    }

    override fun onPause() {
        super.onPause()
        stateJob?.cancel()
    }

    override fun onClick(id: Int) {
        val intent = Intent(this, Class.forName("com.example.mycapstone.detail.DetailActivity"))
        intent.putExtra("id",id)
        startActivity(intent)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}