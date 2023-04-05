package com.example.mainmedapp.presentation.screen.signIn

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentSignInBinding
import com.example.mainmedapp.presentation.utils.ErrorDialog

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: Фрагмент авторизации
 */

class SignInFragment : Fragment() {
    private lateinit var binding:FragmentSignInBinding
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.isEnabled = false
        binding.progressBar.visibility = View.GONE

        //Проверка на корректный email
        binding.TIETEmail.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.TILEmail.helperText = submitEmail()
                if(binding.TILEmail.helperText == null){
                    binding.btnNext.isEnabled = true
                }
            }
            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.btnNext.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.sendCode(binding.TIETEmail.text.toString())
        }

        //Паттерн observer
        viewModel.sendCodeLiveData.observe(viewLifecycleOwner){response ->
            binding.progressBar.visibility = View.GONE
            if(response.code() == 200){
                Toast.makeText(requireContext(), "Код успешно отправлен", Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                bundle.putString("email", binding.TIETEmail.text.toString())
                this.findNavController().navigate(R.id.action_signInFragment_to_emailCodeFragment, bundle)
            }else{
                ErrorDialog("Ошибка ${response.code()}", response.message()).show(requireActivity().supportFragmentManager, "ErrorDialog")
            }
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.GONE
            ErrorDialog("Отсутствует подключение к интернету", "Проверьте соединение").show(requireActivity().supportFragmentManager, "ErrorDialog")
        }
    }

    //Функция проверка соответствия email паттерну
    private fun submitEmail(): String? {
        return if(!Patterns.EMAIL_ADDRESS.matcher(binding.TIETEmail.text.toString()).matches()){
            "Неправильно введен email"
        }else{
            null
        }

    }

}