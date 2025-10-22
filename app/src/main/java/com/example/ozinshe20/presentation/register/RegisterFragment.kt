package com.example.ozinshe20.presentation.register

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe20.R
import com.example.ozinshe20.databinding.FragmentRegisterBinding
import com.example.ozinshe20.provideNavigationHost

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }


        viewModel.registerResponse.observe(viewLifecycleOwner) {
            binding.tvErrorTextPasswordAndServer.visibility = View.GONE
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            it.accessToken
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        viewModel.errorResponse.observe(viewLifecycleOwner) {
            showError(it)
        }

        binding.btnShowPassword.setOnClickListener {
            togglePasswordVisibility(binding.editTextPassword)
        }

        binding.btnShowCheckPassword.setOnClickListener {
            togglePasswordVisibility(binding.editTextCheckPassword)
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val checkPassword = binding.editTextCheckPassword.text.toString()

            val isValidEmail = emailValidation(email)
            val isValidPassword = validationPassword(password, binding.editTextPassword)
            val isValidCheckPassword = validationPassword(checkPassword, binding.editTextCheckPassword)

            if (isValidEmail && isValidPassword && isValidCheckPassword) {
                if (password == checkPassword) {
                    viewModel.registerUser(email, password)
                } else {
                    showError("Құпия сөздер сәйкес келмейді")
                    binding.editTextCheckPassword.setBackgroundResource(R.drawable.background_edittext_12dp_error)
                }
            } else {
                validationEmail(isValidEmail)
                validationPassword(password, binding.editTextPassword)
                validationPassword(checkPassword, binding.editTextCheckPassword)
            }
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun togglePasswordVisibility(editText: EditText) {
        editText.transformationMethod = if (editText.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            PasswordTransformationMethod.getInstance()
        } else {
            HideReturnsTransformationMethod.getInstance()
        }
    }

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private fun emailValidation(email: String): Boolean {
        return email.matches(emailPattern.toRegex())
    }

    private fun validationEmail(isValid: Boolean) {
        if (isValid) {
            binding.tvTextErrorEmail.visibility = View.GONE
            binding.editTextEmail.setBackgroundResource(R.drawable.background_edittext_focus_action_12dp)
        } else {
            binding.tvTextErrorEmail.visibility = View.VISIBLE
            binding.editTextEmail.setBackgroundResource(R.drawable.background_edittext_12dp_error)
        }
    }

    private fun validationPassword(password: String, editText: EditText): Boolean {
        return if (password.length < 6) {
            binding.tvErrorTextPasswordAndServer.text = "Құпия сөз 6 символдан кем болмауы тиіс"
            binding.tvErrorTextPasswordAndServer.visibility = View.VISIBLE
            editText.setBackgroundResource(R.drawable.background_edittext_12dp_error)
            false
        } else {
            binding.tvErrorTextPasswordAndServer.visibility = View.GONE
            editText.setBackgroundResource(R.drawable.background_edittext_focus_action_12dp)
            true
        }
    }

    private fun showError(message: String) {
        if (message != "HTTP 400 ") {
            binding.tvErrorTextPasswordAndServer.text = message
        }
        binding.tvErrorTextPasswordAndServer.visibility = View.VISIBLE
    }
}