package com.example.ozinshe20.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
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
        adapterMainMovies.setOnClickMovieListener(object :RcViewItemClickMainMoviesCallback{
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }
        })

        viewModel.mainMoviesResponse.observe(viewLifecycleOwner) {
            adapterMainMovies.submitList(it)
        }

        viewModel.getMoviesByCategoryMain(token)

        viewModel.moviesByCategoryMainModel.observe(viewLifecycleOwner) { categories ->
            fun adapterMovies(position: Int, recyclerView: RecyclerView, categoryNameView: TextView) {
                val adapterMoviesByCategory = MoviesByCategoryMainAdapter()
                recyclerView.adapter = adapterMoviesByCategory
                categoryNameView.text = categories[position].categoryName
                adapterMoviesByCategory.submitList(categories[position].movies)

                adapterMoviesByCategory.setOnClickMovieListener(object : RcViewItemClickMainMoviesCallback {
                    override fun onClick(movieId: Int) {
                        val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                        findNavController().navigate(action)
                    }
                })
            }

            adapterMovies(0, binding.rcMoviesCategory, binding.categoryName)
            adapterMovies(1, binding.rcMoviesCategory1, binding.categoryName1)
            adapterMovies(2, binding.rcMoviesCategory2, binding.categoryName2)
        }
    }
}
