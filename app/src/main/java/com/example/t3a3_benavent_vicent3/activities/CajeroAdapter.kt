package com.example.t3a3_benavent_vicent3.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t3a3_benavent_vicent3.R
import com.example.t3a3_benavent_vicent3.databinding.ItemAtmBinding
import com.example.t3a3_benavent_vicent3.pojo.CajeroEntity

class CajeroAdapter (private var cajeros : MutableList<CajeroEntity>, private var listener: onClickListener)
    : RecyclerView.Adapter<CajeroAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAtmBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_atm, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cajeros.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cajero = cajeros.get(position)

        with(holder) {
            binding.nombre.text = cajero.direccion
            binding.cantidad.text = cajero.latitud.toString()
            binding.cardView.setOnClickListener {
                listener.onClick(cajero)
            }

        }
    }

    fun add (cajero : MutableList<CajeroEntity>) {
        for (i in cajero) {
            this.cajeros.add(i)
        }
        notifyDataSetChanged()
    }

}