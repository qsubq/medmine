package com.example.mainmedapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mainmedapp.R

/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: Главная и единственная activity
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //Переход с Launch темы на обычную
        setTheme(R.style.Theme_MainMedApp)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}