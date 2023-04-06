package com.example.mainmedapp.presentation.screen.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentProfileBinding
import com.example.mainmedapp.domain.model.RequestUpdateProfileModel
import com.example.mainmedapp.presentation.utils.ErrorDialog


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    //Костыль для корректного отображения SearchBar'a
    override fun onResume() {
        super.onResume()
        val searchBar = requireActivity().findViewById<AppCompatImageView>(R.id.img_search)
        searchBar.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){uri ->
            if(uri != null ){
                binding.shapeableImageView.setImageURI(uri)
            }

        }
        binding.shapeableImageView.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.btnNext.isEnabled = false
        binding.progressBar.visibility = View.GONE

        //Паттерн observer на livedata во viewModel
        viewModel.updateProfileLiveData.observe(viewLifecycleOwner) { response ->
            binding.progressBar.visibility = View.GONE
            if (response.code() == 200) {
                Toast.makeText(requireContext(), "Профиль успешно обновлен", Toast.LENGTH_SHORT)
                    .show()
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
        //Проверка на заполненность полей
        binding.TIETFirstName.addTextChangedListener(object : TextWatcher {
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
        binding.TIETLastName.addTextChangedListener(object : TextWatcher {
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
        binding.TIETMiddleName.addTextChangedListener(object : TextWatcher {
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
        binding.TIETDateName.addTextChangedListener(object : TextWatcher {
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
        binding.TIETPol.addTextChangedListener(object : TextWatcher {
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
            viewModel.updateProfile(
                (
                        RequestUpdateProfileModel(
                            binding.TIETFirstName.text.toString(),
                            binding.TIETLastName.text.toString(),
                            binding.TIETMiddleName.text.toString(),
                            binding.TIETDateName.text.toString(),
                            binding.TIETPol.text.toString()
                        )
                        )
            )

        }
    }

}