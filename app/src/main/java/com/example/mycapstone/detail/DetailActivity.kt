package com.example.mycapstone.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.core.data.remote.ApiResponse
import com.example.core.utils.DataMapper
import com.example.core.utils.ItemCoin
import com.example.mycapstone.R
import com.example.mycapstone.databinding.ActivityDetailBinding
import com.example.mycapstone.utils.TypeConverter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = "Detail"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel: DetailViewModel by viewModel()

        val id = intent.extras?.getInt("id")

        viewModel.observeFavoriteStatus(id!!)


        lifecycleScope.launch {
            viewModel.detail(id).collect {
                when (it) {
                    is ApiResponse.Loading -> {

                    }
                    is ApiResponse.Empty -> {

                    }
                    is ApiResponse.Success -> {
                        val detail = it.data
                        val usd = detail.quote?.USD
                        with(binding) {
                            Glide.with(this@DetailActivity)
                                .load(ItemCoin.getUrl(detail.id))
                                .into(logoImageView)
                            name.text = detail.name
                            price.text = TypeConverter.doubleToString(usd?.price)
                            cmcRank.text = detail.cmc_rank.toString()
                            symbol.text = detail.symbol.toString()
                            totalSuply.text = TypeConverter.doubleToString(detail.total_supply)
                            maxSuply.text = TypeConverter.doubleToString(detail.max_supply)
                            platform.text = detail.platform?.name.toString()
                            dateAdd.text = detail.date_added
                            marketCap.text = TypeConverter.doubleToString(usd?.market_cap)
                            percent1h.text = TypeConverter.doubleToString(usd?.percent_change_1h)
                            percent24h.text = TypeConverter.doubleToString(usd?.percent_change_24h)
                            percent7d.text = TypeConverter.doubleToString(usd?.percent_change_7d)
                            volume24h.text = TypeConverter.doubleToString(usd?.volume_24h)
                            volumeChange24h.text =
                                TypeConverter.doubleToString(usd?.volume_change_24h)
                            lastUpdated.text = detail.last_updated

                            favoriteImageView.setOnClickListener {
                                viewModel.toggleFavorite(
                                    id,
                                    DataMapper.coinDetailModelToEntity(detail)
                                )
                            }

                        }
                    }
                    is ApiResponse.Error -> {

                    }
                }
            }


        }

        lifecycleScope.launch {
            viewModel.isCoinFavorite.collect {
                if (it) {
                    binding.favoriteImageView.setImageResource(R.drawable.favorite_on)
                } else {
                    binding.favoriteImageView.setImageResource(R.drawable.favorite_off)
                }

            }


        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}