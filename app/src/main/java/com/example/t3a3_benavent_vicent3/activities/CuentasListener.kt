package com.example.t3a3_benavent_vicent3.activities

import com.example.bancoapiprofe.pojo.Cuenta

interface CuentasListener {
    fun onCuentaSeleccionada(cuenta: Cuenta)
}