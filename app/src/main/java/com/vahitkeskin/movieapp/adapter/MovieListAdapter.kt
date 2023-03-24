package com.vahitkeskin.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vahitkeskin.movieapp.databinding.ItemMovieListBinding
import com.vahitkeskin.movieapp.model.MovieListModel

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */
class MovieListAdapter(
    private val paymentList: List<MovieListModel>
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
        holder.bind(paymentBean)
    }

    override fun getItemCount(): Int = paymentList.size

    class MovieListViewHolder(private val itemBinding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movieListModel: MovieListModel) {
            Glide.with(itemView.context)
                .load(movieListModel.image)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(itemBinding.ivListItem)
        }
    }
}