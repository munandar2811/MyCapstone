package com.example.mycapstone.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.data.domain.model.CoinModel
import com.example.mycapstone.databinding.ItemCoinBinding
import com.example.mycapstone.utils.TypeConverter


class AdapterMain : PagingDataAdapter<CoinModel, AdapterMain.ViewHolder>(DIFF_CALLBACK) {

    private var listener: OnClick? = null

    fun setListener(listener: OnClick) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.bind(data)
    }


    class ViewHolder(private val itemCoinBinding: ItemCoinBinding, private val listener: OnClick?) :
        RecyclerView.ViewHolder(itemCoinBinding.root) {

        fun bind(coin: CoinModel) {

            with(itemCoinBinding) {
                Glide.with(root)
                    .load(coin.logoUrl)
                    .into(logo)

                cmcRank.text = coin.rank.toString()
                symbol.text = coin.symbol.toString()
                price.text = TypeConverter.doubleToString(coin.price)
                change24hour.text = TypeConverter.doubleToString(coin.percentChange24h)

                itemCoin.setOnClickListener {
                    listener?.onClick(coin.id!!)
                }
            }

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CoinModel>() {
            override fun areItemsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}