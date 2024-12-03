package com.example.t3a3_benavent_vicent3.activities

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_ennama.adapters.MovimientoAdapter
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.example.bancoapiprofe.pojo.Cliente
import com.example.bancoapiprofe.pojo.Cuenta
import com.example.bancoapiprofe.pojo.Movimiento
import com.example.t3a3_benavent_vicent3.databinding.ActivityMovementsBinding

class MovementsActivity : AppCompatActivity() {

    private lateinit var movimientoAdapter: MovimientoAdapter
    private lateinit var binding: ActivityMovementsBinding
    private lateinit var listaCuentas: List<Cuenta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        val mbo = MiBancoOperacional.getInstance(this)

        // Obtenemos las cuentas del cliente
        listaCuentas = mbo?.getCuentas(cliente) as? List<Cuenta> ?: emptyList()

        // Configuramos el Spinner
        val spCuentas: Spinner = binding.spCuentas
        val cuentas = listaCuentas.map { cuenta ->
            "${cuenta.getBanco()}-${cuenta.getSucursal()}-${cuenta.getDc()}-${cuenta.getNumeroCuenta()}"
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spCuentas.adapter = adapter

        // Configuramos el RecyclerView
        binding.reciclerView.layoutManager = LinearLayoutManager(this)
        binding.reciclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        // Listener del Spinner
        spCuentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val cuentaSeleccionada = listaCuentas[position]
                val movimientos = getMovimientos(cuentaSeleccionada)

                // Configuramos el adaptador del RecyclerView
                movimientoAdapter = MovimientoAdapter(movimientos, object : MovimientoAdapter.OnMovementClickListener {
                    override fun onMovementClick(movimiento: Movimiento) {
                        // Manejar el clic en un movimiento
                        // Por ejemplo, mostrar un diálogo con los detalles
                    }
                })
                binding.reciclerView.adapter = movimientoAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada si no se selecciona nada
            }
        }

        // Ajustamos el padding para la barra de sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getMovimientos(cuenta: Cuenta): List<Movimiento> {
        val mbo = MiBancoOperacional.getInstance(this)
        return mbo?.getMovimientos(cuenta) as? List<Movimiento> ?: emptyList()
    }
}
