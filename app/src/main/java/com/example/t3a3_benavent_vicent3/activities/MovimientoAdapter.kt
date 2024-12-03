package com.example.banco_ennama.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bancoapiprofe.pojo.Movimiento
import com.example.t3a3_benavent_vicent3.R
import com.example.t3a3_benavent_vicent3.databinding.ItemMovimientoBinding

class MovimientoAdapter(
    private val movimientos: List<Movimiento>,
    private val listener: OnMovementClickListener
) : RecyclerView.Adapter<MovimientoAdapter.ViewHolder>() {


    interface OnMovementClickListener {
        fun onMovementClick(movimiento: Movimiento)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovimientoBinding.bind(view)

        init {
            itemView.setOnClickListener {
                val movimiento = movimientos[adapterPosition]
                listener.onMovementClick(movimiento)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movimiento, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movimientos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movimiento = movimientos[position]
        with(holder.binding) {
            nombre.text = movimiento.getDescripcion()
            importe.text = movimiento.getImporte().toString()
        }
    }
}
