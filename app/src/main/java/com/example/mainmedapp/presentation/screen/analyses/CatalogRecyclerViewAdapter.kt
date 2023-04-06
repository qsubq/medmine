package com.example.mainmedapp.presentation.screen.analyses

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: RecyclerView адаптер для каталога
 */

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mainmedapp.R
import com.example.mainmedapp.data.localDataSource.CartEntity
import com.example.mainmedapp.databinding.CatalogRecyclerViewItemBinding
import com.example.mainmedapp.domain.model.ResponseGetCatalogModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class CatalogRecyclerViewAdapter :
    RecyclerView.Adapter<CatalogRecyclerViewAdapter.CatalogViewHolder>() {
    class CatalogViewHolder(val binding: CatalogRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listOfItems: List<ResponseGetCatalogModel> = emptyList()
    private var parentFragment: AnalysesFragment? = null

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

        holder.itemView.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(holder.itemView.context)
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout)
            holder.itemView.findViewById<MaterialTextView>(R.id.tv_name_modal).text = listOfItems[position].name
            holder.itemView.findViewById<MaterialTextView>(R.id.tv_desc_modal).text = listOfItems[position].description
            holder.itemView.findViewById<MaterialTextView>(R.id.tv_preparation_modal).text = listOfItems[position].preparation
            holder.itemView.findViewById<MaterialTextView>(R.id.tv_time_modal).text = listOfItems[position].time_result

            bottomSheetDialog.show()

        }

        holder.binding.btnAdd.setOnClickListener {


            if (holder.binding.btnAdd.text == "Добавить") {
                parentFragment?.insertItem(
                    CartEntity(
                        listOfItems[position].id,
                        listOfItems[position].name,
                        listOfItems[position].price.toInt(),
                        1
                    )
                )
                holder.binding.btnAdd.text = "Убрать"
            } else {
                holder.binding.btnAdd.text = "Добавить"
                parentFragment?.deleteItem(
                    CartEntity(
                        listOfItems[position].id,
                        listOfItems[position].name,
                        listOfItems[position].price.toInt(),
                        1
                    )
                )
            }
        }
    }

    //Функция обновления RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    fun setupList(list: List<ResponseGetCatalogModel>) {
        listOfItems = list
        notifyDataSetChanged()
    }

    fun setupParentFragment(fragment: AnalysesFragment) {
        parentFragment = fragment
    }

}
