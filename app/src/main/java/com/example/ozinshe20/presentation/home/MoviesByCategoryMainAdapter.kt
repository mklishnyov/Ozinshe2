package com.example.ozinshe20.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.ozinshe20.data.model.MainMoviesResponseItem
import com.example.ozinshe20.data.model.Movy
import com.example.ozinshe20.databinding.ItemCategoryMainBinding

class MoviesByCategoryMainAdapter: RecyclerView.Adapter<MoviesByCategoryMainAdapter.MoviesByCategoryMainHolder>()  {
    private val diffCallback = object : DiffUtil.ItemCallback<Movy>() {
        override fun areItemsTheSame(
            oldItem: Movy,
            newItem: Movy
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Movy,
            newItem: Movy
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(listMainMoviesByCategory: List<Movy>) {
        differ.submitList(listMainMoviesByCategory)
    }

    private var listenerClickAtItem: RcViewItemClickMainMoviesCallback? = null

    fun setOnClickMovieListener(listener: RcViewItemClickMainMoviesCallback){
        this.listenerClickAtItem = listener
    }

    inner class MoviesByCategoryMainHolder(private var binding: ItemCategoryMainBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItem(MovieItem: Movy){
            val fixedLink = MovieItem.poster.link.replace("http://api.ozinshe.com", "http://apiozinshe.mobydev.kz")

            Glide.with(itemView.context)
                .load(fixedLink)
                .transform(CenterCrop(), RoundedCorners(24))
                .into(binding.imgCategoryMovie)

            binding.tvTitleMovie.text = MovieItem.name
            binding.tvCategoryMovie.text = MovieItem.categories[0].name
            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(MovieItem.id)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesByCategoryMainHolder {
        return MoviesByCategoryMainHolder(ItemCategoryMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MoviesByCategoryMainHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int = differ.currentList.size
}