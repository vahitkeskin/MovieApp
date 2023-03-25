package com.vahitkeskin.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vahitkeskin.movieapp.R
import com.vahitkeskin.movieapp.databinding.ItemNetworkStateBinding
import com.vahitkeskin.movieapp.util.visibleIf

/**
 * @authot: Vahit Keskin
 * creared on 26.03.2023
 */
class MovieLoadStateAdapter(
    private val adapter: MovieListAdapter
) : LoadStateAdapter<MovieLoadStateAdapter.NetworkStateItemViewHolder>() {

    class NetworkStateItemViewHolder(
        private val binding: ItemNetworkStateBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener {
                retryCallback.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar visibleIf (loadState is LoadState.Loading)
                retryButton visibleIf (loadState is LoadState.Error)
                errorMsg visibleIf
                        !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorMsg.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        NetworkStateItemViewHolder(
            ItemNetworkStateBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_network_state, parent, false)
            )
        ) {
            adapter.retry()
        }
}