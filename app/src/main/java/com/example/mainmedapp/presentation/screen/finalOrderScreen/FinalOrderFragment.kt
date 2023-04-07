package com.example.mainmedapp.presentation.screen.finalOrderScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentFInalOrderBinding

/**
Автор: Каргин Максим (участник №3)
Дата создания: 07.04.2023
Назначение: Класс экрана "Заказ успешно оплачен"
 */


class FinalOrderFragment : Fragment() {
    private lateinit var binding :FragmentFInalOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentFInalOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOnMain.setOnClickListener {
            this.findNavController().navigate(R.id.action_finalOrderFragment_to_containerFragment)
        }
    }
}