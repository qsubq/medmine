package com.example.mainmedapp.data.localDataSource

import androidx.room.*
import androidx.room.Dao

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