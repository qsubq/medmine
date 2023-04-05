package com.example.mainmedapp.presentation.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: Класс для показа диалогового окна
 */

class ErrorDialog(private val title:String, private val message:String):DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK"){dialog, _ ->
            dialog.cancel()
        }
        return builder.create()
    }
}