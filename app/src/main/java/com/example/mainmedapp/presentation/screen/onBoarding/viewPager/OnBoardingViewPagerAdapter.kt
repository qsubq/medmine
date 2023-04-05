package com.example.mainmedapp.presentation.screen.onBoarding.viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: Адаптер для ViewPager'а в OnBoarding
 */

class OnBoardingViewPagerAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> OnBoardingViewPagerFragment1()
            1 -> OnBoardingViewPagerFragment2()
            else -> OnBoardingViewPagerFragment3()
        }
    }
}