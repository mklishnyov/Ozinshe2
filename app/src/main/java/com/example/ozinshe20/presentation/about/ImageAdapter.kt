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
import com.example.ozinshe20.databinding.ItemImageBinding

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ScreenshotXX>() {
        override fun areItemsTheSame(
            oldItem: ScreenshotXX,
            newItem: ScreenshotXX
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ScreenshotXX,
            newItem: ScreenshotXX
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<ScreenshotXX>) = differ.submitList(list)

    private var listenerClickAtItem: RcViewItemClickImageCallback? = null

    fun setOnClickImageListener(listener: RcViewItemClickImageCallback) {
        this.listenerClickAtItem = listener
    }

    inner class ImageHolder(private var binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(image: ScreenshotXX) {
            val fixedLink =
                image.link.replaceFirst("http://api.ozinshe.com", "http://apiozinshe.mobydev.kz")

            Glide.with(itemView.context)
                .load(fixedLink)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(binding.imageView)

            binding.root.setOnClickListener {
                listenerClickAtItem?.onClick(fixedLink)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageHolder {
        return ImageHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int = differ.currentList.size
}
