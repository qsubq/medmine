package com.example.mainmedapp.presentation.screen.onBoarding.viewPager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentOnBoardingViewPager3Binding
import com.example.mainmedapp.presentation.screen.onBoarding.OnBoardingViewModel

/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: Третий viewPager фрагмент
 */

class OnBoardingViewPagerFragment3 : Fragment() {
    private lateinit var binding:FragmentOnBoardingViewPager3Binding
    private val viewModel by viewModels<OnBoardingViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingViewPager3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //При нажатии на кнопку сохраняется информация о том, что onBoarding уже просмотрен и просходит переход на следующий экран
        binding.tvSkip.setOnClickListener {
            viewModel.setIsAlreadySeenOnBoardingUseCase()
            this.findNavController().navigate(R.id.action_onBoardingFragment_to_signInFragment)
        }
    }
}