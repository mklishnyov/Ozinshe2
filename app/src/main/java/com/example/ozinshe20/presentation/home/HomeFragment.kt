package com.example.ozinshe20.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ozinshe20.data.SharedProvider
import com.example.ozinshe20.databinding.FragmentHomeBinding
import com.example.ozinshe20.provideNavigationHost

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        provideNavigationHost()?.setNavigationVisibility(true)

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMainMovies(token)
        viewModel.getMoviesByCategoryMain(token)

        val adapterMainMovies = MainMovieAdapter()
        binding.rcMainMovies.adapter = adapterMainMovies
        viewModel.mainMoviesResponse.observe(viewLifecycleOwner) {
            adapterMainMovies.submitList(it)
        }

        viewModel.getMoviesByCategoryMain(token)
        val adapterMoviesByCategory = MoviesByCategoryMainAdapter()
        viewModel.moviesByCategoryMainModel.observe(viewLifecycleOwner) {
            binding.rcMoviesCategory.adapter = adapterMoviesByCategory
            binding.categoryName.text = it[0].categoryName
            adapterMoviesByCategory.submitList(it[0].movies)
        }
    }
}
