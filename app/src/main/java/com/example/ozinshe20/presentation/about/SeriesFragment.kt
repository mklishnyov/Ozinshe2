package com.example.ozinshe20.presentation.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ozinshe20.R
import com.example.ozinshe20.data.SharedProvider
import com.example.ozinshe20.databinding.FragmentSeriesBinding
import com.example.ozinshe20.provideNavigationHost

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding
    private val viewModel: SeriesViewModel by viewModels()
    private val args: SeriesFragmentArgs by navArgs()



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

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getSeriesById(token, args.movieId)
        val adapterSeries = SeriesAdapter()
        viewModel.videosResponse.observe(viewLifecycleOwner) {
            adapterSeries.submitList(it[0].videos)
        }
        binding.rcViewSeriesFragment.adapter = adapterSeries
        binding.rcViewSeriesFragment.addItemDecoration(
            CustomDividerItemDecoration(
                getDrawable(requireContext(), R.drawable.divider_1dp_grey)!!
            )
        )

        adapterSeries.setOnClickVideoListener(object : RcViewItemClickVideoCallback{
            override fun onVideoItemClick(videoLink: String) {
                val action = SeriesFragmentDirections.actionSeriesFragmentToVideoFragment(videoLink)
                findNavController().navigate(action)
            }

        })
    }
}