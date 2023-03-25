package com.vahitkeskin.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vahitkeskin.movieapp.databinding.ItemMovieListBinding
import com.vahitkeskin.movieapp.model.MovieListModel
import com.vahitkeskin.movieapp.util.loadImage
import com.vahitkeskin.movieapp.util.visibleIf

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */
class MovieListAdapter(
    private val paymentList: List<MovieListModel>,
    private val onClickMovieItem: (image: String) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val itemBinding = ItemMovieListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val paymentBean: MovieListModel = paymentList[position]
        holder.itemView.rootView.setOnClickListener {
            onClickMovieItem.invoke(paymentBean.image)
        }
        holder.bind(paymentBean)
    }

    override fun getItemCount(): Int = paymentList.size

    class MovieListViewHolder(private val itemBinding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movieListModel: MovieListModel) {
            itemBinding.ivListItem loadImage movieListModel.image
            itemBinding.ivListItem visibleIf true
        }
    }
}