package com.example.ozinshe20.presentation.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.ozinshe20.data.model.MainMoviesResponseItem
import com.example.ozinshe20.databinding.ItemMainMoviesBinding

class MainMovieAdapter: RecyclerView.Adapter<MainMovieAdapter.MainMovieHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<MainMoviesResponseItem>() {
        override fun areItemsTheSame(
            oldItem: MainMoviesResponseItem,
            newItem: MainMoviesResponseItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MainMoviesResponseItem,
            newItem: MainMoviesResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(listMoviesMain: List<MainMoviesResponseItem>) = differ.submitList(listMoviesMain)

    private var listenerClickAtItem: RcViewItemClickMainMoviesCallback? = null

    fun setOnClickMovieListener(listener: RcViewItemClickMainMoviesCallback){
        this.listenerClickAtItem = listener
    }

    inner class MainMovieHolder(private var binding: ItemMainMoviesBinding): RecyclerView.ViewHolder(binding.root){
        fun bindItem(mainMovieItem: MainMoviesResponseItem){
            val fixedLink = mainMovieItem.link.replace("http://api.ozinshe.com", "http://apiozinshe.mobydev.kz")

            Glide.with(itemView.context)
                .load(fixedLink)
                .apply(RequestOptions().transform(RoundedCorners(12)))
                .into(binding.imgMainMovie)

            binding.tvTextTitle.text = mainMovieItem.movie.name
            binding.tvTextDescription.text = mainMovieItem.movie.description
            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(mainMovieItem.id)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainMovieHolder {
        return MainMovieHolder(ItemMainMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainMovieHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int = differ.currentList.size
}