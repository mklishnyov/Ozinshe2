package com.example.ozinshe20.presentation.profile

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.ozinshe20.R
import com.example.ozinshe20.data.SharedProvider
import com.example.ozinshe20.databinding.BottomsheetSelectLanguageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class SelectLanguageBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetSelectLanguageBinding
    private var languageSelectedListener: OnLanguageSelectedLIstener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetSelectLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun setOnLanguageSelectedListener(listener: OnLanguageSelectedLIstener) {
        languageSelectedListener = listener
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
            btnSelectEnglish.setOnClickListener {
                selectedIcon(true, false, false)
                changeLanguage("English")
            }
            btnSelectKazakh.setOnClickListener {
                selectedIcon(false, true, false)
                changeLanguage("Қазақша")
            }
            btnSelectRussian.setOnClickListener {
                selectedIcon(false, false, true)
                changeLanguage("Русский")
            }
        }
    }

    fun systemLanguageChange(codeLanguage: String) {
        SharedProvider(requireContext()).safeLanguage(codeLanguage)
        val locale = Locale(codeLanguage)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)

        findNavController().navigate(
            R.id.profileFragment,
            arguments,
            NavOptions.Builder().setPopUpTo(R.id.profileFragment, true).build()
        )
    }

    fun selectedIcon(en: Boolean, kk: Boolean, ru: Boolean) {
        binding.apply {
            ivCheckEnglish.visibility = if (en) View.VISIBLE else View.INVISIBLE
            ivCheckKazakh.visibility = if (kk) View.VISIBLE else View.INVISIBLE
            ivCheckRussian.visibility = if (ru) View.VISIBLE else View.INVISIBLE
        }
    }

    fun changeLanguage(language: String) {
        when(language) {
            "English" -> {
                systemLanguageChange("en")
                languageSelectedListener?.onLanguageSelected("English")
            }
            "Қазақша" -> {
                systemLanguageChange("kk")
                languageSelectedListener?.onLanguageSelected("Қазақша")
            }
            "Русский" -> {
                systemLanguageChange("ru")
                languageSelectedListener?.onLanguageSelected("Русский")
            }
        }
    }
}