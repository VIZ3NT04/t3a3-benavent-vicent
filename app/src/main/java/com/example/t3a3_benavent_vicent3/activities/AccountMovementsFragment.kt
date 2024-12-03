package com.example.t3a3_benavent_vicent3.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_ennama.adapters.MovimientoAdapter
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.example.bancoapiprofe.pojo.Cuenta
import com.example.bancoapiprofe.pojo.Movimiento
import com.example.t3a3_benavent_vicent3.databinding.FragmentAccountMovementsBinding

class AccountMovementsFragment : Fragment() {

    private lateinit var movimientoAdapter: MovimientoAdapter
    private lateinit var binding: FragmentAccountMovementsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountMovementsBinding.inflate(inflater, container, false)

        val cuenta = arguments?.getSerializable("Cuenta") as? Cuenta

        if (cuenta != null) {
            val movimientos = getMovimientos(cuenta)
            movimientoAdapter = MovimientoAdapter(movimientos, object : MovimientoAdapter.OnMovementClickListener {
                override fun onMovementClick(movimiento: Movimiento) {

                }
            })

            binding.reciclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = movimientoAdapter
                addItemDecoration(
                    DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
                )
            }
        }

        return binding.root
    }

    private fun getMovimientos(cuenta: Cuenta): List<Movimiento> {
        val mbo = MiBancoOperacional.getInstance(requireContext())
        return mbo?.getMovimientos(cuenta) as? MutableList<Movimiento> ?: arrayListOf()
    }
}