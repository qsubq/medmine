package com.example.mainmedapp.presentation.screen.analyses

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.NewsRecyclerViewItemBinding
import com.example.mainmedapp.domain.model.ResponseGetNewsModel
import com.squareup.picasso.Picasso

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: Моделька ответа создания профиля
 */

class NewsRecyclerViewAdapter : RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {
    class NewsViewHolder(val binding: NewsRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listOfItems: List<ResponseGetNewsModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            NewsRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.binding.tvName.text = listOfItems[position].name
        holder.binding.tvDesc.text = listOfItems[position].description
        holder.binding.tvPrice.text =
            holder.itemView.context.getString(R.string.price, listOfItems[position].price)

        //Загрузка и автоматическое кэширование изображения
        Picasso.get().load(listOfItems[position].image).into(holder.binding.imgNews)
    }

    //Функция обновления RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    fun setupList(list: List<ResponseGetNewsModel>) {
        listOfItems = list
        notifyDataSetChanged()
    }
}