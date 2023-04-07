package com.example.mainmedapp.data.localDataSource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
Автор: Каргин Максим (участник №3)
Дата создания: 07.04.2023
Назначение: Таблица пациентов
 */


@Entity(tableName = "patients")
data class PatientEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo
    val name:String,

    @ColumnInfo
    val pol:String
)
