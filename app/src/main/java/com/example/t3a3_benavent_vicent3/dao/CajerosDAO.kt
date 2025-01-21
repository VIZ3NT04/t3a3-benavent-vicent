package com.example.t3a3_benavent_vicent3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.t3a3_benavent_vicent3.pojo.CajeroEntity

@Dao
interface CajerosDAO {
    @Query("SELECT * FROM CajerosEntity")
    fun getAllCajeros(): MutableList<CajeroEntity>

    @Insert
    fun addCajero(cajeroEntity: CajeroEntity)

    @Update
    fun updateCajero(cajeroEntity: CajeroEntity)

    @Delete
    fun deleteCajero(cajeroEntity: CajeroEntity)


}