package com.example.ozinshe20.presentation.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ozinshe20.data.OnboardingModel
import com.example.ozinshe20.databinding.ItemViewpagerOnboardingBinding

class OnboardingAdapter(): RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    private val onboardingModelList = mutableListOf<OnboardingModel>()

    fun submitList(list: List<OnboardingModel>){
        onboardingModelList.clear()
        onboardingModelList.addAll(list)
        notifyDataSetChanged()
    }

    inner class OnboardingViewHolder(private val binding: ItemViewpagerOnboardingBinding): RecyclerView.ViewHolder(binding.root){
        fun bindItem(onboardingModel: OnboardingModel){
            binding.imgTvOnboarding.setImageResource(onboardingModel.imageId)
            binding.tvTitleOnboarding.text = onboardingModel.title
            binding.tvDescriptionOnboarding.text = onboardingModel.description
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingViewHolder {
        return OnboardingViewHolder(ItemViewpagerOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: OnboardingViewHolder,
        position: Int
    ) {
        holder.bindItem(onboardingModelList[position])
    }

    override fun getItemCount(): Int {
        return onboardingModelList.size
    }
}