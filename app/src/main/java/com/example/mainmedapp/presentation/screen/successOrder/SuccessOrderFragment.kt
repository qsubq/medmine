package com.example.mainmedapp.presentation.screen.successOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentSuccessOrderBinding

/**
Автор: Каргин Максим (участник №3)
Дата создания: 07.04.2023
Назначение: Preloader процесса оплаты
 */


class SuccessOrderFragment : Fragment() {
    private lateinit var binding:FragmentSuccessOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuccessOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Переход к следующему экрану
        this.findNavController().navigate(R.id.action_successOrderFragment_to_finalOrderFragment)
    }
}