package com.example.ozinshe20.presentation.about

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ozinshe20.R
import com.example.ozinshe20.data.SharedProvider
import com.example.ozinshe20.data.model.MovieIdResponse
import com.example.ozinshe20.databinding.FragmentAboutBinding
import com.example.ozinshe20.provideNavigationHost

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private val args: AboutFragmentArgs by navArgs()
    private val viewModel: AboutViewModel by viewModels()
    private var favoriteState: Boolean = false

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

        binding.btnBackAboutFragment.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMoviesById(token, args.movieId)

        viewModel.moviesByIDResponse.observe(viewLifecycleOwner) {
            binding.btnPlayAboutFragment.setOnClickListener { click ->
                if (it.video != null) {
                    val action = AboutFragmentDirections.actionAboutFragmentToVideoFragment(it.video?.link?: "")
                    findNavController().navigate(action)
                } else {
                    val action = AboutFragmentDirections.actionAboutFragmentToSeriesFragment(it.id)
                    findNavController().navigate(action)
                }
            }
            binding.textBolimder.setOnClickListener {
                val action = AboutFragmentDirections.actionAboutFragmentToSeriesFragment(it.id)
                findNavController().navigate(action)
            }
            binding.btnNextAllMovie.setOnClickListener {
                val action = AboutFragmentDirections.actionAboutFragmentToSeriesFragment(it.id)
                findNavController().navigate(action)
            }
            val adapterScreenshot = ImageAdapter()
            adapterScreenshot.submitList(it.screenshots)
            binding.rcViewScreenshots.adapter = adapterScreenshot
            adapterScreenshot.setOnClickImageListener(object : RcViewItemClickImageCallback {
                override fun onClick(link: String) {
                    val action = AboutFragmentDirections.actionAboutFragmentToImageFragment(link)
                    findNavController().navigate(action)
                }
            })

            val fixedLink = it.poster.link.replaceFirst("http://api.ozinshe.com", "http://apiozinshe.mobydev.kz")
            Glide.with(requireContext())
                .load(fixedLink)
                .into(binding.tvPoster)
            binding.run {
                btnBackAboutFragment.setOnClickListener {
                    requireActivity().onBackPressed()
                }

                if (it.favorite) {
                    favoriteState = true
                    btnFavoriteAboutFragment.background = resources.getDrawable(R.drawable.ic_bookmark_filled, null)
                } else {
                    favoriteState = false
                    btnFavoriteAboutFragment.background = resources.getDrawable(R.drawable.ic_bookmark, null)
                }

                textTitleMovie.text = it.name
                textTvDescription.text = it.description
                textTvAdditionalInfoYear.text = it.year.toString()
                textTvProducer.text = it.producer
                textTvDirector.text = it.director

                var additionalInfo = " "
                for (i in it.genres) {
                    additionalInfo += "· ${i.name} "
                }
                textTvGenres.text = additionalInfo

                textTvDescription.post {
                    if (textTvDescription.lineCount <= 1) {
                        btnMoreDescription.visibility = View.GONE
                        fadingEdgeLayoutDescription.setFadeSizes(0, 0, 0, 0)
                    } else {
                        btnMoreDescription.visibility = View.VISIBLE
                        btnMoreDescription.setOnClickListener {
                            if (textTvDescription.maxLines == 100) {
                                textTvDescription.maxLines = 7
                                btnMoreDescription.text = "Толығырақ"
                                fadingEdgeLayoutDescription.setFadeSizes(0, 0, 120, 0)
                            } else {
                                textTvDescription.maxLines = 100
                                btnMoreDescription.text = "Жасыру"
                                fadingEdgeLayoutDescription.setFadeSizes(0, 0, 0, 0)
                            }
                        }
                    }
                }

                if (it.video == null) {
                    textTvBolimder.text = "${it.seasonCount} сезон, ${it.seriesCount} серия"
                    textBolimder.setOnClickListener {
                        val action = AboutFragmentDirections.actionAboutFragmentToSeriesFragment(it.id)
                        findNavController().navigate(action)
                    }
                    btnNextAllMovie.setOnClickListener {
                        val action = AboutFragmentDirections.actionAboutFragmentToSeriesFragment(it.id)
                        findNavController().navigate(action)
                    }
                } else {
                    textTvBolimder.visibility = View.GONE
                    textBolimder.visibility = View.GONE
                    btnNextAllMovie.visibility = View.GONE
                }
            }
            binding.btnFavoriteAboutFragment.setOnClickListener { click ->
                if (!favoriteState) {
                    viewModel.addFavorite(token, MovieIdResponse(args.movieId))
                } else {
                    viewModel.deleteFavorite(token, MovieIdResponse(args.movieId))
                }
            }
        }
        viewModel.favoriteState.observe(viewLifecycleOwner) {
            if (it) {
                favoriteState = true
                binding.btnFavoriteAboutFragment.background = resources.getDrawable(R.drawable.ic_bookmark_filled, null)
            } else {
                favoriteState = false
                binding.btnFavoriteAboutFragment.background = resources.getDrawable(R.drawable.ic_bookmark, null)
            }
        }
    }
}