package com.example.ozinshe20.presentation.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ozinshe20.R
import com.example.ozinshe20.databinding.FragmentSeriesBinding
import com.example.ozinshe20.provideNavigationHost

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.setNavigationVisibility(false)

        binding.toolbarSeriesIncluded.tvTitleToolbar.text = "Бөлімдер"
        binding.toolbarSeriesIncluded.btnBackToolbar.setOnClickListener {
            findNavController().popBackStack()
        }

        val adapterSeries = SeriesAdapter()
        binding.rcViewSeriesFragment.adapter = adapterSeries

        adapterSeries.setOnClickVideoListener(object : RcViewItemClickVideoCallback{
            override fun onVideoItemClick(videoLink: String) {
                val action = SeriesFragmentDirections.actionSeriesFragmentToVideoFragment(videoLink)
                findNavController().navigate(action)
            }

        })
    }
}