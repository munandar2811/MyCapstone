package com.example.mycapstone.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycapstone.databinding.ItemLoadstateBinding


class LoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadingStateAdapter.LoadingStateViewholder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewholder {
        val binding =
            ItemLoadstateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingStateViewholder(binding, retry)
    }

    override fun onBindViewHolder(holder: LoadingStateViewholder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadingStateViewholder(
        private val binding: ItemLoadstateBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMsg.isVisible = loadState is LoadState.Error
        }
    }
}