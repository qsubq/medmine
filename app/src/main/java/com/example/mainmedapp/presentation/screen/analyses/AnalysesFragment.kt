package com.example.mainmedapp.presentation.screen.analyses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.FragmentAnalysesBinding
import com.example.mainmedapp.presentation.utils.ErrorDialog

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: Фрагмент экрана "Анализы/Главная"
 */

class AnalysesFragment : Fragment() {
    private lateinit var binding: FragmentAnalysesBinding
    private val viewModel by viewModels<AnalysesViewModel>()

    //Делегаты для RecyclerView адаптеров
    private val newsAdapter by lazy {
        NewsRecyclerViewAdapter()
    }
    private val catalogAdapter by lazy {
        CatalogRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnalysesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeData()
        getData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
    }

    override fun onResume() {
        super.onResume()

        //Костыль для корректного отображение search bar'а
        val searchBar = requireActivity().findViewById<AppCompatImageView>(R.id.img_search)
        searchBar.visibility = View.VISIBLE
    }

    //Функция инициализации RecyclerView адаптеров
    private fun initRecyclerView() {
        binding.rvNews.adapter = newsAdapter
        binding.rvCatalog.adapter = catalogAdapter
    }

    //Функция получения данных
    private fun getData() {
        binding.progressBar1.visibility = View.VISIBLE
        binding.progressBar2.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false

        viewModel.getNews()
        viewModel.getCatalog()
    }

    //Функция, содержащая паттерн observer на liveData
    private fun observeData() {

        //Observe отсутствия интернет соединения
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            binding.progressBar1.visibility = View.GONE
            binding.progressBar2.visibility = View.GONE
            binding.swipeRefreshLayout.isRefreshing = false

            ErrorDialog("Отсутствует подключение к интернету", "Проверьте соединение").show(
                requireActivity().supportFragmentManager,
                "ErrorDialog"
            )
        }

        //Подписка на изменение данных новостей
        viewModel.newsLiveData.observe(viewLifecycleOwner) { response ->
            binding.progressBar1.visibility = View.GONE
            binding.progressBar2.visibility = View.GONE
            binding.swipeRefreshLayout.isRefreshing = false

            if (response.code() == 200) {
                response.body()?.let { newsAdapter.setupList(it) }
            } else {
                ErrorDialog("Ошибка ${response.code()}", response.message()).show(
                    requireActivity().supportFragmentManager,
                    "ErrorDialog"
                )
            }
        }

        //Подписка на изменение данных каталога
        viewModel.catalogLiveData.observe(viewLifecycleOwner) { response ->
            binding.progressBar1.visibility = View.GONE
            binding.progressBar2.visibility = View.GONE
            binding.swipeRefreshLayout.isRefreshing = false

            if (response.code() == 200) {
                response.body()?.let { catalogAdapter.setupList(it) }
            } else {
                ErrorDialog("Ошибка ${response.code()}", response.message()).show(
                    requireActivity().supportFragmentManager,
                    "ErrorDialog"
                )
            }
        }
    }

}