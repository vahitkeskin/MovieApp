package com.vahitkeskin.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vahitkeskin.movieapp.databinding.ItemMovieListBinding
import com.vahitkeskin.movieapp.model.ListResult

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */
class MovieListAdapter(
    private val onClickMovieItem: (image: ListResult) -> Unit
) : PagingDataAdapter<ListResult, MovieListAdapter.MovieListViewHolder>(LIST_COMPARATOR) {

    companion object {
        private val LIST_COMPARATOR = object : DiffUtil.ItemCallback<ListResult>() {
            override fun areItemsTheSame(oldItem: ListResult, newItem: ListResult): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListResult, newItem: ListResult): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val itemBinding = ItemMovieListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val listResult = getItem(position) as ListResult
        holder.itemBinding.listResult = listResult
        holder.itemView.rootView.setOnClickListener {
            onClickMovieItem.invoke(listResult)
        }
    }

    class MovieListViewHolder(val itemBinding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}