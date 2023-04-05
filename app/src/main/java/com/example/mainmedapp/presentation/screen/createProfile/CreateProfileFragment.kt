package com.example.mainmedapp.presentation.screen.createProfile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentCreateProfileBinding
import com.example.mainmedapp.domain.model.RequestCreateProfileModel
import com.example.mainmedapp.presentation.utils.ErrorDialog

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: CreateProfileFragment
 */

class CreateProfileFragment : Fragment() {
    private lateinit var binding :FragmentCreateProfileBinding
    private val viewModel by viewModels<CreateProfileViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateProfileBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.isEnabled = false
        binding.progressBar.visibility = View.GONE
        binding.tvSkip.setOnClickListener {
            this.findNavController().navigate(R.id.action_createProfileFragment_to_toBeContinuedFragment)
        }

        //Паттерн observer на livedata во viewModel
        viewModel.createProfileLiveData.observe(viewLifecycleOwner){response ->
            binding.progressBar.visibility = View.GONE
            if(response.code() == 200){
                Toast.makeText(requireContext(), "Профиль успешно создан", Toast.LENGTH_SHORT).show()
                this.findNavController().navigate(R.id.action_createProfileFragment_to_toBeContinuedFragment)
            }else{
                ErrorDialog("Ошибка ${response.code()}", response.message()).show(requireActivity().supportFragmentManager, "ErrorDialog")
            }
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.GONE
            ErrorDialog("Отсутствует подключение к интернету", "Проверьте соединение").show(requireActivity().supportFragmentManager, "ErrorDialog")
        }


        //Проверка на заполненность полей
        binding.TIETFirstName.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled = (!binding.TIETFirstName.text.isNullOrEmpty()
                        && !binding.TIETLastName.text.isNullOrEmpty()
                        && !binding.TIETMiddleName.text.isNullOrEmpty()
                        && !binding.TIETDateName.text.isNullOrEmpty()
                        && !binding.TIETPol.text.isNullOrEmpty())
            }
        })
        binding.TIETLastName.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled = (!binding.TIETFirstName.text.isNullOrEmpty()
                        && !binding.TIETLastName.text.isNullOrEmpty()
                        && !binding.TIETMiddleName.text.isNullOrEmpty()
                        && !binding.TIETDateName.text.isNullOrEmpty()
                        && !binding.TIETPol.text.isNullOrEmpty())
            }
        })
        binding.TIETMiddleName.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled = (!binding.TIETFirstName.text.isNullOrEmpty()
                        && !binding.TIETLastName.text.isNullOrEmpty()
                        && !binding.TIETMiddleName.text.isNullOrEmpty()
                        && !binding.TIETDateName.text.isNullOrEmpty()
                        && !binding.TIETPol.text.isNullOrEmpty())
            }
        })
        binding.TIETDateName.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled = (!binding.TIETFirstName.text.isNullOrEmpty()
                        && !binding.TIETLastName.text.isNullOrEmpty()
                        && !binding.TIETMiddleName.text.isNullOrEmpty()
                        && !binding.TIETDateName.text.isNullOrEmpty()
                        && !binding.TIETPol.text.isNullOrEmpty())
            }
        })
        binding.TIETPol.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled = (!binding.TIETFirstName.text.isNullOrEmpty()
                        && !binding.TIETLastName.text.isNullOrEmpty()
                        && !binding.TIETMiddleName.text.isNullOrEmpty()
                        && !binding.TIETDateName.text.isNullOrEmpty()
                        && !binding.TIETPol.text.isNullOrEmpty())
            }
        })

        //Создание профиля по нажатию на кнопку
        binding.btnNext.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.createProfile(RequestCreateProfileModel(
                0,
                binding.TIETFirstName.text.toString(),
                binding.TIETLastName.text.toString(),
                binding.TIETMiddleName.text.toString(),
                binding.TIETDateName.text.toString(),
                binding.TIETPol.text.toString(),
                ""
            ))
        }


    }
}