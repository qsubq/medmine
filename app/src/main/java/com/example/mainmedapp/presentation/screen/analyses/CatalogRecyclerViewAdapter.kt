package com.example.mainmedapp.presentation.screen.analyses

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: RecyclerView адаптер для каталога
 */

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainmedapp.R
import com.example.mainmedapp.databinding.CatalogRecyclerViewItemBinding
import com.example.mainmedapp.domain.model.ResponseGetCatalogModel

class CatalogRecyclerViewAdapter :
    RecyclerView.Adapter<CatalogRecyclerViewAdapter.CatalogViewHolder>() {
    class CatalogViewHolder(val binding: CatalogRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listOfItems: List<ResponseGetCatalogModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val binding = CatalogRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CatalogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.binding.tvName.text = listOfItems[position].name
        holder.binding.tvDays.text = listOfItems[position].time_result
        holder.binding.tvPrice.text =
            holder.itemView.context.getString(R.string.price, listOfItems[position].price)
    }

    //Функция обновления RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    fun setupList(list: List<ResponseGetCatalogModel>) {
        listOfItems = list
        notifyDataSetChanged()
    }
}
