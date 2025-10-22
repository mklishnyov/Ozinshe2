package com.example.ozinshe20.presentation.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.ozinshe20.R
import com.example.ozinshe20.databinding.FragmentLoginBinding
import com.example.ozinshe20.provideNavigationHost

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }


        viewModel.loginResponse.observe(viewLifecycleOwner) {
            binding.tvErrorTextPasswordAndServer.visibility = View.GONE
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            it.accessToken
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        viewModel.errorResponse.observe(viewLifecycleOwner) {
            showError(it)
        }

        binding.btnShowPassword.setOnClickListener {
            togglePasswordVisibility()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            val isValidEmail = emailValidation(email)
            val isValidPassword = validationPassword(password)

            if (isValidEmail && isValidPassword) {
                viewModel.loginUser(email, password)
            } else {
                validationEmail(isValidEmail)
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    fun togglePasswordVisibility() {
        val passwordEditText = binding.editTextPassword
        passwordEditText.transformationMethod = if (passwordEditText.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            PasswordTransformationMethod.getInstance()
        } else {
            HideReturnsTransformationMethod.getInstance()
        }
    }

    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    fun emailValidation(email: String): Boolean {
        return email.matches(emailPattern.toRegex())
    }

    fun validationEmail(isValid: Boolean) {
        if (isValid) {
            binding.tvTextErrorEmail.visibility = View.GONE
            binding.editTextEmail.setBackgroundResource(R.drawable.background_edittext_focus_action_12dp)
        } else {
            binding.tvTextErrorEmail.visibility = View.VISIBLE
            binding.editTextEmail.setBackgroundResource(R.drawable.background_edittext_12dp_error)
        }
    }

    fun validationPassword(password: String): Boolean {
        return if (password.length < 6) {
            binding.tvErrorTextPasswordAndServer.text = "Құпия сөз 6 символдан кем болмауы тиіс"
            binding.tvErrorTextPasswordAndServer.visibility = View.VISIBLE
            binding.editTextPassword.setBackgroundResource(R.drawable.background_edittext_12dp_error)
            false
        } else {
            binding.tvErrorTextPasswordAndServer.visibility = View.GONE
            binding.editTextPassword.setBackgroundResource(R.drawable.background_edittext_focus_action_12dp)
            true
        }
    }

    fun showError(message: String) {
        binding.tvErrorTextPasswordAndServer.text = "Қате орын алды"
        binding.tvErrorTextPasswordAndServer.visibility = View.VISIBLE
    }

}