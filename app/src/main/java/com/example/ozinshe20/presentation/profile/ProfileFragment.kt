package com.example.ozinshe20.presentation.profile

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ozinshe20.R
import com.example.ozinshe20.data.SharedProvider
import com.example.ozinshe20.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch
import java.util.Locale

class ProfileFragment : Fragment(), OnLanguageSelectedLIstener {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        systemLanguage()

        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        if (Build.VERSION.SDK_INT >= 26) {
            transaction.setReorderingAllowed(false)
        }
        transaction.detach(this).attach(this).commit()

        viewModel.language.observe(viewLifecycleOwner) {
            binding.tvSelectedLanguage.text = it
        }

        binding.btnChangeLanguage.setOnClickListener {
            val bottomSheet = SelectLanguageBottomSheet()
            bottomSheet.setOnLanguageSelectedListener(this)
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }

        binding.toolbarProfile.btnExit.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            )
        }

        binding.userEmail.text = SharedProvider(requireContext()).getEmail()
    }

    override fun onLanguageSelected(language: String) {
        viewModel.selectLanguage(language)
    }

    private fun systemLanguage() {
        when(SharedProvider(requireContext()).getLanguage()) {
            "en" -> {
                val locale = Locale("en")
                Locale.setDefault(locale)
                val config = resources.configuration
                config.setLocale(locale)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "English"
            }
            "kk" -> {
                val locale = Locale("kk")
                Locale.setDefault(locale)
                val config = resources.configuration
                config.setLocale(locale)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "Қазақша"
            }
            "ru" -> {
                val locale = Locale("ru")
                Locale.setDefault(locale)
                val config = resources.configuration
                config.setLocale(locale)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "Русский"
            }
            else -> {
                val locale = Locale("kk")
                Locale.setDefault(locale)
                val config = resources.configuration
                config.setLocale(locale)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "Қазақша"
            }
        }
    }
}