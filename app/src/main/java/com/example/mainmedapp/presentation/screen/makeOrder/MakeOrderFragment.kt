package com.example.mainmedapp.presentation.screen.makeOrder

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mainmedapp.R
import com.example.mainmedapp.data.localDataSource.PatientEntity
import com.example.mainmedapp.databinding.FragmentMakeOrderBinding
import com.example.mainmedapp.domain.model.ItemsModel
import com.example.mainmedapp.domain.model.PatientModel
import com.example.mainmedapp.domain.model.RequestCreateOrderModel
import com.example.mainmedapp.presentation.utils.ErrorDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

/**
Автор: Каргин Максим (участник №3)
Дата создания: 07.04.2023
Назначение: Класс для экрана "Оформление заказа"
 */


class MakeOrderFragment : Fragment() {
    private lateinit var binding: FragmentMakeOrderBinding
    private val viewModel by viewModels<MakeOrderViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMakeOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCreateOrder.isEnabled = false
        binding.progressBar.visibility = View.GONE

        viewModel.getItemsPrice()

        //Слушатель нажатий на кнопку назад
        binding.btnBack.setOnClickListener {
            this.findNavController().navigate(R.id.action_makeOrderFragment_to_cartFragment)
        }


        //Создание заказа и переход к следующему экрану
        binding.btnCreateOrder.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE

            viewModel.createOrder(
                RequestCreateOrderModel(
                    binding.TIETAddress.text.toString(),
                    binding.TIETDateAndTime.text.toString(),
                    binding.TIETNumber.text.toString(),
                    binding.TIETComment.text.toString(),
                    "111",
                    listOf(PatientModel("MyPatient", listOf(ItemsModel(2,"300"))))
                )
            )
            this.findNavController().navigate(R.id.action_makeOrderFragment_to_successOrderFragment)
        }

        //Подписка на создание заказа
        viewModel.createOrderLiveData.observe(viewLifecycleOwner) { response ->
            binding.progressBar.visibility = View.GONE
            if (response.code() == 200) {
                Toast.makeText(requireContext(), "Заказ успешно создан", Toast.LENGTH_SHORT)
                    .show()
            } else {
                ErrorDialog(
                    "Ошибка ${response.code()}",
                    response.message()
                ).show(requireActivity().supportFragmentManager, "ErrorDialog")
            }
        }

        //Подписка на отсутствие интернет соединения
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            ErrorDialog("Отсутствует подключение к интернету", "Проверьте соединение").show(
                requireActivity().supportFragmentManager,
                "ErrorDialog"
            )
        }

        //Подписка на изменение итоговой стоимости
        viewModel.priceLiveData.observe(viewLifecycleOwner){price ->
            if(price != null){
                binding.tvCreateOrderPrice.text = getString(R.string.price, price.toString())
            }else{
                binding.tvCreateOrderPrice.text = getString(R.string.price, "0")
            }
        }

        //Проверка на заполненность обязательных полей
        binding.TIETAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnCreateOrder.isEnabled = (!binding.TIETAddress.text.isNullOrEmpty()
                        && !binding.TIETNumber.text.isNullOrEmpty()
                        && !binding.TIETPatient.text.isNullOrEmpty()
                        && !binding.TIETDateAndTime.text.isNullOrEmpty())
            }
        })
        binding.TIETNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnCreateOrder.isEnabled = (!binding.TIETAddress.text.isNullOrEmpty()
                        && !binding.TIETNumber.text.isNullOrEmpty()
                        && !binding.TIETPatient.text.isNullOrEmpty()
                        && !binding.TIETDateAndTime.text.isNullOrEmpty())
            }
        })
        binding.TIETDateAndTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnCreateOrder.isEnabled = (!binding.TIETAddress.text.isNullOrEmpty()
                        && !binding.TIETNumber.text.isNullOrEmpty()
                        && !binding.TIETPatient.text.isNullOrEmpty()
                        && !binding.TIETDateAndTime.text.isNullOrEmpty())
            }
        })
        binding.TIETPatient.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnCreateOrder.isEnabled = (binding.TIETAddress.text.isNullOrEmpty()
                        && !binding.TIETNumber.text.isNullOrEmpty()
                        && !binding.TIETPatient.text.isNullOrEmpty()
                        && !binding.TIETDateAndTime.text.isNullOrEmpty())
            }
        })

    }

}