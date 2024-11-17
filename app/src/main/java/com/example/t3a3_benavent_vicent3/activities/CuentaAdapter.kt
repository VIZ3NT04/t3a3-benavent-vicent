package com.example.t3a3_benavent_vicent3.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bancoapiprofe.pojo.Cuenta
import com.example.t3a3_benavent_vicent3.R
import com.example.t3a3_benavent_vicent3.databinding.ItemCuentaBinding


class CuentaAdapter(private val cuentas: List<Cuenta>): RecyclerView.Adapter<CuentaAdapter.ViewHolder>(){

    private lateinit var context: Context

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding = ItemCuentaBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Inflar la vista item_tarea en el Recycler
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_cuenta, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cuentas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //Asignamos el

        val cuenta = cuentas.get(position)
        with(holder) {
            val nombre : String = cuenta.getBanco()+"-"+cuenta.getSucursal()+"-"+cuenta.getDc()+"-"+cuenta.getNumeroCuenta()
            binding.nombre.text = nombre
            binding.cantidad.text = cuenta.getSaldoActual().toString()

        }

    }
}