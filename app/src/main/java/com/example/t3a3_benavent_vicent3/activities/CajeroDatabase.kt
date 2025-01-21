package com.example.t3a3_benavent_vicent3.activities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.t3a3_benavent_vicent3.dao.CajerosDAO
import com.example.t3a3_benavent_vicent3.pojo.CajeroEntity

@Database(entities = arrayOf(CajeroEntity::class), version = 1)
abstract class CajeroDatabase : RoomDatabase() {
    abstract fun cajerosDao(): CajerosDAO
}