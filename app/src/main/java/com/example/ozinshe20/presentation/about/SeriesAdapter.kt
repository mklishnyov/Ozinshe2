package com.example.ozinshe20.presentation.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.ozinshe20.data.model.ScreenshotXX
import com.example.ozinshe20.data.model.VideoResponse
import com.example.ozinshe20.databinding.ItemImageBinding
import com.example.ozinshe20.databinding.ItemSeriesBinding

class SeriesAdapter: RecyclerView.Adapter<SeriesAdapter.SeriesHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<VideoResponse>() {
        override fun areItemsTheSame(
            oldItem: VideoResponse,
            newItem: VideoResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: VideoResponse,
            newItem: VideoResponse
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<VideoResponse>) = differ.submitList(list)

    private var listenerClickAtItem: RcViewItemClickVideoCallback? = null

    fun setOnClickVideoListener(listener: RcViewItemClickVideoCallback) {
        this.listenerClickAtItem = listener
    }

    inner class SeriesHolder(private var binding: ItemSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(video: VideoResponse) {
            binding.apply {
                Glide.with(itemView)
                    .load("http://img.youtube.com/vi/${video.videos[0].link}/maxresdefault.jpg")
                    .into(binding.imgTvSeries)
                textSeries.text = "${video.number}-ші бөлім"
                root.setOnClickListener {
                    listenerClickAtItem?.onVideoItemClick(video.videos[0].link)
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