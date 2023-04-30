package com.example.mycapstone.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycapstone.R
import com.example.mycapstone.databinding.ActivityMainBinding
import com.example.mycapstone.detail.DetailActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnClick {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = "My Capstone"

        val viewModel: MainViewModel by viewModel()

        val adapter = AdapterMain()
        adapter.setListener(this)

        lifecycleScope.launch {
            viewModel.listCoinModel.collect {
                adapter.submitData(lifecycle, it)
            }
        }


        with(binding) {
            rvMain.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            rvMain.setHasFixedSize(true)
            rvMain.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )

            fabFavorite.setOnClickListener {
                val intent = Intent(
                    this@MainActivity,
                    Class.forName("com.example.favorite.favorite.FavoriteActivity")
                )
                startActivity(intent)
            }
        }


    }

    override fun onClick(id: Int) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bahasa -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                true
            }
            else -> {
                false
            }
        }

    }


}