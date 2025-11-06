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
            binding.run {
                btnBackAboutFragment.setOnClickListener {
                    requireActivity().onBackPressed()
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
                        // Navigation to Bolimber Fragment
                    }
                    btnNextAllMovie.setOnClickListener {
                        // Navigation to Bolimder Fragment
                    }
                } else {
                    textTvBolimder.visibility = View.GONE
                    textBolimder.visibility = View.GONE
                    btnNextAllMovie.visibility = View.GONE
                }
            }
        }
    }
}