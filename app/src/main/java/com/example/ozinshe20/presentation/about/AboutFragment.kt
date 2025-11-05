package com.example.ozinshe20.presentation.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ozinshe20.R
import com.example.ozinshe20.data.SharedProvider
import com.example.ozinshe20.databinding.FragmentAboutBinding
import com.example.ozinshe20.provideNavigationHost

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private val args: AboutFragmentArgs by navArgs()
    private val viewModel: AboutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMoviesById(token, args.movieId)

        viewModel.moviesByIDResponse.observe(viewLifecycleOwner) {
            val fixedLink = it.poster.link.replaceFirst("http://api.ozinshe.com", "http://apiozinshe.mobydev.kz")
            Glide.with(requireContext())
                .load(fixedLink)
                .into(binding.tvPoster)
            binding.textTitleMovie.text = it.name
            binding.textTvDescription.text = it.description
            binding.textTvAdditionalInfoYear.text = it.year.toString()
            binding.textTvProducer.text = it.producer
            binding.textTvDirector.text = it.director

        }
    }
}