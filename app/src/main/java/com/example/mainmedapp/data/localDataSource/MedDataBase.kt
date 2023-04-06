package com.example.mainmedapp.data.localDataSource

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [CartEntity::class])
abstract class MedDataBase:RoomDatabase() {
    abstract fun getDao():Dao

    companion object{
        private var dataBase:MedDataBase? = null

        fun getInstance(context:Application):MedDataBase{
            return if(dataBase == null){
                dataBase = Room.databaseBuilder(context, MedDataBase::class.java,"db").build()
                dataBase as MedDataBase
            }else{
                dataBase as MedDataBase
            }
        }
    }
}