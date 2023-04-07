package com.example.mainmedapp.data.localDataSource

import androidx.room.*
import androidx.room.Dao

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: Doa для общения с Room
 */

@Dao
interface Dao {

    @Insert(entity = CartEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(item:CartEntity)

    @Delete(entity = CartEntity::class)
    suspend fun deleteCartItem(item: CartEntity)

    @Query("SELECT * FROM cart")
    suspend fun getAllItems():List<CartEntity>

    @Query("SELECT SUM(price * amount) FROM cart")
    suspend fun getItemsPrice():Int

    @Query("DELETE FROM cart")
    suspend fun deleteAll()
}