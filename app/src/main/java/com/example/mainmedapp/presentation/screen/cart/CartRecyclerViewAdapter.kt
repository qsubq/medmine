package com.example.mainmedapp.presentation.screen.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mainmedapp.R
import com.example.mainmedapp.data.localDataSource.CartEntity
import com.example.mainmedapp.databinding.CartRecyclerViewItemBinding

class CartRecyclerViewAdapter : RecyclerView.Adapter<CartRecyclerViewAdapter.CartViewHolder>() {
    class CartViewHolder(val binding: CartRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listOfItems: List<CartEntity> = emptyList()
    private var parentFragment: CartFragment? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            CartRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.binding.tvName.text = listOfItems[position].name
        holder.binding.tvPrice.text =
            holder.itemView.context.getString(
                R.string.price,
                listOfItems[position].price.toString()
            )
        holder.binding.tvPatient.text =  holder.itemView.context.getString(
            R.string.patient,
            listOfItems[position].amount.toString()
        )


        holder.binding.imgMinus.setOnClickListener {
            if (listOfItems[position].amount > 1) {
                parentFragment?.insertItem(
                    CartEntity(
                        listOfItems[position].id,
                        listOfItems[position].name,
                        listOfItems[position].price.toInt(),
                        listOfItems[position].amount - 1
                    )
                )
            }

        }
        holder.binding.imgPlus.setOnClickListener {
            parentFragment?.insertItem( CartEntity(
                listOfItems[position].id,
                listOfItems[position].name,
                listOfItems[position].price.toInt(),
                listOfItems[position].amount + 1
            ))
        }
        holder.binding.imgDeleteItem.setOnClickListener {
            parentFragment?.deleteItem(
                CartEntity(
                    listOfItems[position].id,
                    listOfItems[position].name,
                    listOfItems[position].price,
                    listOfItems[position].amount
                )
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupList(list: List<CartEntity>) {
        listOfItems = list
        notifyDataSetChanged()
    }

    fun setupParentFragment(fragment: CartFragment) {
        parentFragment = fragment
    }
}