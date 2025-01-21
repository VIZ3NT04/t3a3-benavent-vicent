package com.example.t3a3_benavent_vicent3.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "CajerosEntity")
data class CajeroEntity (
    @PrimaryKey(true) val id : Int = 0,
    @ColumnInfo(name = "direccion" ) var direccion : String?,
    @ColumnInfo(name = "latitud" ) var latitud : Double?,
    @ColumnInfo(name = "longitud" ) var longitud : Double?,
    @ColumnInfo(name = "zoom" ) val zoom : String?,
): Serializable{
    constructor(direccion: String?, latitud: Double?, longitud: Double?, zoom: String?):
            this(0, direccion, latitud, longitud, zoom)
}