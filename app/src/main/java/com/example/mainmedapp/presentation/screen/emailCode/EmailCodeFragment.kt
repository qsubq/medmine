package com.example.mainmedapp.presentation.screen.emailCode

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentEmailCodeBinding
import com.example.mainmedapp.presentation.utils.ErrorDialog

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: Фрагмент на введение кода email
 */

class EmailCodeFragment : Fragment() {
    private lateinit var binding: FragmentEmailCodeBinding
    private val viewModel by viewModels<EmailCodeViewModel>()
    private val email by lazy {
        arguments?.getString("email")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmailCodeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.GONE

        //Листенер на кол-ва символов
        binding.TIETCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 4) {
                    binding.progressBar.visibility = View.VISIBLE
                    email?.let { viewModel.signIn(it, binding.TIETCode.text.toString()) }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        //Паттерн observer
        viewModel.sendCodeLiveData.observe(viewLifecycleOwner) { response ->
            binding.progressBar.visibility = View.GONE
            if (response.code() == 200) {
                Toast.makeText(requireContext(), "Код успешно отправлен", Toast.LENGTH_SHORT).show()
            } else {
                ErrorDialog(
                    "Ошибка ${response.code()}",
                    response.message()
                ).show(requireActivity().supportFragmentManager, "ErrorDialog")
            }
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            ErrorDialog("Отсутствует подключение к интернету", "Проверьте соединение").show(
                requireActivity().supportFragmentManager,
                "ErrorDialog"
            )
        }
        viewModel.signInLiveData.observe(viewLifecycleOwner) { response ->
            binding.progressBar.visibility = View.GONE
            if (response.code() == 200) {
                val sharedPref =
                    requireActivity().getSharedPreferences("tokenAndPassword", MODE_PRIVATE)
                sharedPref.edit().putString("token", response.body()?.token).apply()
                this.findNavController()
                    .navigate(R.id.action_emailCodeFragment_to_createPasswordFragment)
            } else {
                if (response.code() == 422) {
                    binding.TILCode.helperText = "Неверный код"
                } else {
                    ErrorDialog("Ошибка ${response.code()}", response.message()).show(
                        requireActivity().supportFragmentManager,
                        "ErrorDialog"
                    )
                }

            }
        }

        binding.btnBack.setOnClickListener {
            this.findNavController().navigate(R.id.action_emailCodeFragment_to_signInFragment)
        }
        startCountTimer()
    }


    //Таймер обратного отсчета
    private fun startCountTimer() {
        object : CountDownTimer(45000, 1000) {
            override fun onTick(p0: Long) {
                binding.tvSendCodeAgain.text =
                    requireContext().getString(R.string.send_code_again, (p0 / 1000).toString())
            }

            override fun onFinish() {
                //Проверка на то был ли введен неправильный код
                if (binding.TILCode.helperText != null) {
                    email?.let { viewModel.sendCode(it) }
                }
            }
        }.start()
    }
}