package com.example.mainmedapp.data.localDataSource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo
    val name:String,

    @ColumnInfo
    val price:Int,

    @ColumnInfo
    val amount:Int
)
