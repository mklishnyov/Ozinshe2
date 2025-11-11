package com.example.ozinshe20.presentation.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.ozinshe20.data.model.VideoXXX
import com.example.ozinshe20.databinding.ItemSeriesBinding

class SeriesAdapter: RecyclerView.Adapter<SeriesAdapter.SeriesHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<VideoXXX>() {
        override fun areItemsTheSame(
            oldItem: VideoXXX,
            newItem: VideoXXX
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: VideoXXX,
            newItem: VideoXXX
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<VideoXXX>) = differ.submitList(list)

    private var listenerClickAtItem: RcViewItemClickVideoCallback? = null

    fun setOnClickVideoListener(listener: RcViewItemClickVideoCallback) {
        this.listenerClickAtItem = listener
    }

    inner class SeriesHolder(private var binding: ItemSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(video: VideoXXX) {
            binding.apply {
                Glide.with(itemView)
                    .load("http://img.youtube.com/vi/${video.link}/maxresdefault.jpg")
                    .transform(CenterCrop(), RoundedCorners(40))
                    .into(binding.imgTvSeries)
                textSeries.text = "${video.number}-ші бөлім"
                root.setOnClickListener {
                    listenerClickAtItem?.onVideoItemClick(video.link)
                }
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesHolder {
        return SeriesHolder(
            ItemSeriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SeriesHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int = differ.currentList.size
}