package com.example.ozinshe20.presentation.profile

import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ozinshe20.databinding.BottomsheetSelectLanguageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class SelectLanguageBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetSelectLanguageBinding

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


    }

    fun systemLanguageChange(codeLanguage: String) {
        val locale = Locale(codeLanguage)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
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