package com.example.mainmedapp.presentation.screen.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentContainerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: Фрагмент для экрана, содержащего ViewPager2
 */

class ContainerFragment : Fragment() {
    private lateinit var binding: FragmentContainerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContainerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager2.adapter = ViewPagerAnalysesAdapter(this)
        binding.viewPager2.isUserInputEnabled = false

        //Настройка и связывание TabLayout с ViewPager'ом
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.analyses_icon)
                    tab.text = "Анализы"
                }
                1 -> {
                    tab.setIcon(R.drawable.results_icon)
                    tab.text = "Результаты"
                }
                2 -> {
                    tab.setIcon(R.drawable.support_icon)
                    tab.text = "Поддержка"
                }
                else -> {
                    tab.setIcon(R.drawable.profile_icon)
                    tab.text = "Профиль"
                }
            }
        }.attach()


        //Изменение цвета иконок при выборе пунктов нижнего меню
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                context?.getColor(R.color.blue)?.let { tab?.icon?.setTint(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                context?.getColor(R.color.grey)?.let { tab?.icon?.setTint(it) }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        //Слушатель нажатий на SearchBar
        binding.imgSearch.setOnClickListener {
            this.findNavController().navigate(R.id.action_containerFragment_to_searchFragment)
        }

        binding.cardViewBtnAdd.setOnClickListener {
            this.findNavController().navigate(R.id.action_containerFragment_to_cartFragment)
        }
    }

}