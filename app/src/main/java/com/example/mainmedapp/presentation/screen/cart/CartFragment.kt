package com.example.mainmedapp.presentation.screen.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.data.localDataSource.CartEntity
import com.example.mainmedapp.databinding.FragmentCartBinding
import com.example.mainmedapp.presentation.utils.ErrorDialog
import com.google.android.material.textview.MaterialTextView


class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    private val viewModel by viewModels<CartViewModel>()
    private val rvCartAdapter by lazy{
        CartRecyclerViewAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCart.adapter = rvCartAdapter
        rvCartAdapter.setupParentFragment(this)

        viewModel.getAllItems()

        binding.btnDeleteAll.setOnClickListener {
            viewModel.deleteAll()
        }
        binding.btnBack.setOnClickListener {
            this.findNavController().navigate(R.id.action_cartFragment_to_containerFragment)
        }

        viewModel.itemsLiveData.observe(viewLifecycleOwner) { response ->
            rvCartAdapter.setupList(response)
        }

        viewModel.priceLiveData.observe(viewLifecycleOwner){price ->
            if(price != null){
                binding.tvPriceInCart.text = getString(R.string.price, price.toString())
            }else{
                binding.tvPriceInCart.text = getString(R.string.price, "0")
            }
        }
        binding.btnNext.setOnClickListener {
            this.findNavController().navigate(R.id.action_cartFragment_to_makeOrderFragment)
        }
    }
    fun insertItem(item: CartEntity){
        viewModel.insertItem(item)
    }
    fun deleteItem(item: CartEntity){
        viewModel.deleteItem(item)
    }

}