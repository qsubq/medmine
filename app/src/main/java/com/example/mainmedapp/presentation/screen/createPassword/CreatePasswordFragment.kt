package com.example.mainmedapp.presentation.screen.createPassword

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentCreatePasswordBinding

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: Фрагмент создания пароля
 */

class CreatePasswordFragment : Fragment() {
    private lateinit var binding: FragmentCreatePasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Проверка на уже созданный пароль
        val sharedPrefIsAlready = requireActivity().getSharedPreferences(
            "isAlready",
            Context.MODE_PRIVATE
        )
        if(sharedPrefIsAlready.getBoolean("isAlreadyCreatedPassword",false)){
            this.findNavController().navigate(R.id.action_createPasswordFragment_to_createProfileFragment)
        }


        binding = FragmentCreatePasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Листенер на edit text
        binding.TIETPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 4) {

                    val sharedPrefPassword =
                        requireActivity().getSharedPreferences(
                            "tokenAndPassword",
                            Context.MODE_PRIVATE
                        )
                    sharedPrefPassword.edit().putString("password", binding.TIETPassword.text.toString())
                        .apply()

                    val sharedPrefIsAlready = requireActivity().getSharedPreferences(
                        "isAlready",
                        Context.MODE_PRIVATE
                    )

                    sharedPrefIsAlready.edit().putBoolean("isAlreadyCreatedPassword", true).apply()
                    findNavController().navigate(R.id.action_createPasswordFragment_to_createProfileFragment)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.tvSkip.setOnClickListener {
            this.findNavController().navigate(R.id.action_createPasswordFragment_to_containerFragment)
        }
    }

}