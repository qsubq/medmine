package com.example.mainmedapp.presentation.screen.container

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mainmedapp.presentation.screen.analyses.AnalysesFragment
import com.example.mainmedapp.presentation.screen.toBeContinued.ToBeContinuedFragment

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: ViewPager адаптер для главного экрана
 */

class ViewPagerAnalysesAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AnalysesFragment()
            1 -> ToBeContinuedFragment()
            2 -> ToBeContinuedFragment()
            else -> ToBeContinuedFragment()
        }
    }
}