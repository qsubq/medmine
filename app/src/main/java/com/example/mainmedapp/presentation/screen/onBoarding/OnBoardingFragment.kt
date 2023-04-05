package com.example.mainmedapp.presentation.screen.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentOnBoardingBinding
import com.example.mainmedapp.presentation.screen.onBoarding.viewPager.OnBoardingViewPagerAdapter


/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: OnBoarding фрагмент для показа onBoarding
 */

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private val viewModel by viewModels<OnBoardingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Проверка на то, просмотрен ли уже onBoarding
        if (viewModel.isAlreadySeenUseCase()) {
            this.findNavController()
                .navigate(R.id.action_onBoardingFragment_to_toBeContinuedFragment)
        }
        binding = FragmentOnBoardingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = OnBoardingViewPagerAdapter(this)

    }
}