package com.example.t3a3_benavent_vicent3.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bancoapiprofe.pojo.Movimiento
import com.example.t3a3_benavent_vicent3.R
import com.example.t3a3_benavent_vicent3.databinding.ItemMovimientoBinding

class MovimientoAdapter(private val movimientos: List<Movimiento>) : RecyclerView.Adapter<MovimientoAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovimientoBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movimiento, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movimientos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movimiento = movimientos[position]
        with(holder) {
            binding.nombre.text = movimiento.getDescripcion()
            binding.importe.text = movimiento.getImporte().toString()
        }
    }
}