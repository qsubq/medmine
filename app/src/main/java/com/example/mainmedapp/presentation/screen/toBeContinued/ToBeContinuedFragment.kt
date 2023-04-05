package com.example.mainmedapp.presentation.screen.toBeContinued

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mainmedapp.R

/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: "Продолжение следует" фрагмент
 */

class ToBeContinuedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_to_be_continued, container, false)
    }

}