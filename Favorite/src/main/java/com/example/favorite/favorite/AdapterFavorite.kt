package com.example.favorite.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.data.domain.model.CoinModel
import com.example.mycapstone.databinding.ItemCoinBinding
import com.example.mycapstone.utils.TypeConverter

class AdapterFavorite(private val listener:OnClick,private val coins:List<CoinModel>): RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {




    class ViewHolder(private val binding:ItemCoinBinding,private val listener: OnClick):RecyclerView.ViewHolder(binding.root) {

        fun bind(coin:CoinModel){
            with(binding){

                Glide.with(root)
                    .load(coin.logoUrl)
                    .into(logo)

                cmcRank.text = coin.rank.toString()
                symbol.text = coin.symbol.toString()
                price.text = TypeConverter.doubleToString(coin.price)
                change24hour.text = TypeConverter.doubleToString(coin.percentChange24h)

                itemCoin.setOnClickListener {
                    listener.onClick(coin.id!!)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =ItemCoinBinding.inflate( LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view,listener)
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = coins[position]
        holder.bind(data)
    }
}