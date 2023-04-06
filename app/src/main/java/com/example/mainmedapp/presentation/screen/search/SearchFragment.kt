package com.example.mainmedapp.presentation.screen.search

import android.os.Bundle
import android.view.FocusFinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentSearchBinding

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: Фрагмент для экрана "Главная/Поиск/Начало"
 */


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvBack.setOnClickListener {
            this.findNavController().navigate(R.id.action_searchFragment_to_containerFragment)
        }

        binding.TIETSearch.requestFocus()
    }

}