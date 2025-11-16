package com.example.ozinshe20.presentation.profile

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ozinshe20.data.SharedProvider
import com.example.ozinshe20.databinding.BottomsheetSelectLanguageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class SelectLanguageBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetSelectLanguageBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetSelectLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val defaultLanguage: String = SharedProvider(requireContext()).getLanguage()
        when(defaultLanguage) {
            "en" -> {
                selectedIcon(true, false, false)
            }
            "kk" -> {
                selectedIcon(false, true, false)
            }
            "ru" -> {
                selectedIcon(false, false, true)
            }
        }

        binding.apply {
            btnIconSelectEnglish.setOnClickListener {
                selectedIcon(true, false, false)
                changeLanguage("English")
            }
            btnIconSelectKazakh.setOnClickListener {
                selectedIcon(false, true, false)
                changeLanguage("Қазақша")
            }
            btnIconSelectRussian.setOnClickListener {
                selectedIcon(false, false, true)
                changeLanguage("Русский")
            }
        }
    }

    fun systemLanguageChange(codeLanguage: String) {
        val locale = Locale(codeLanguage)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
    }

    fun selectedIcon(iconEnglish: Boolean, iconKazakh: Boolean, iconRussian: Boolean) {
        binding.apply {
            if (iconEnglish) {
                btnIconSelectEnglish.visibility = View.VISIBLE
            } else {
                btnIconSelectEnglish.visibility = View.GONE
            }
            if (iconKazakh) {
                btnIconSelectKazakh.visibility = View.VISIBLE
            } else {
                btnIconSelectKazakh.visibility = View.GONE
            }
            if (iconRussian) {
                btnIconSelectRussian.visibility = View.VISIBLE
            } else {
                btnIconSelectRussian.visibility = View.GONE
            }
        }
    }

    fun changeLanguage(language: String) {
        when(language) {
            "English" -> {
                systemLanguageChange("en")
            }
            "Қазақша" -> {
                systemLanguageChange("kk")
            }
            "Русский" -> {
                systemLanguageChange("ru")
            }
        }
    }
}