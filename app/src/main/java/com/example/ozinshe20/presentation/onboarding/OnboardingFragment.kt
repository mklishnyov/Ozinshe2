package com.example.ozinshe20.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ozinshe20.R
import com.example.ozinshe20.data.OnboardingInfoList
import com.example.ozinshe20.databinding.FragmentOnboardingBinding
import com.example.ozinshe20.provideNavigationHost

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = OnboardingAdapter()
        adapter.submitList(OnboardingInfoList.onboardingModelList)
        binding.viewPager2OnboardingFragment.adapter = adapter

        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }


        val viewPagerCallBack = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == OnboardingInfoList.onboardingModelList.size - 1) {
                    binding.btnSkipOnboardingFragment.visibility = View.INVISIBLE
                    binding.btnNextOnboardingFragment.visibility = View.VISIBLE
                } else {
                    binding.btnSkipOnboardingFragment.visibility = View.VISIBLE
                    binding.btnNextOnboardingFragment.visibility = View.INVISIBLE
                }
            }
        }

        binding.viewPager2OnboardingFragment.registerOnPageChangeCallback(viewPagerCallBack)
        binding.dotsIndicator.setViewPager2(binding.viewPager2OnboardingFragment)

        binding.btnNextOnboardingFragment.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }
        
        binding.btnSkipOnboardingFragment.setOnClickListener {
            binding.viewPager2OnboardingFragment.currentItem = OnboardingInfoList.onboardingModelList.size - 1
        }
    }
}