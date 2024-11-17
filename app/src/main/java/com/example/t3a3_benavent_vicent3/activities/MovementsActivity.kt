package com.example.t3a3_benavent_vicent3.activities

import android.content.Context
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.example.bancoapiprofe.pojo.Cliente
import com.example.bancoapiprofe.pojo.Cuenta
import com.example.bancoapiprofe.pojo.Movimiento
import com.example.t3a3_benavent_vicent3.R
import com.example.t3a3_benavent_vicent3.databinding.ActivityMovementsBinding

class MovementsActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var movimientoAdapter: MovimientoAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var binding: ActivityMovementsBinding
    private lateinit var listaCuentas: List<Cuenta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        listaCuentas = mbo?.getCuentas(cliente) as? ArrayList<Cuenta> ?: listOf()

        val spCuentas: Spinner = findViewById(R.id.spCuentas)
        val cuentas = listaCuentas.map { cuenta ->
            "${cuenta.getBanco()}-${cuenta.getSucursal()}-${cuenta.getDc()}-${cuenta.getNumeroCuenta()}"
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spCuentas.adapter = adapter

        linearLayoutManager = LinearLayoutManager(this)
        binding.reciclerView.layoutManager = linearLayoutManager

        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.reciclerView.addItemDecoration(itemDecoration)

        spCuentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val cuentaSeleccionada = listaCuentas[position]

                val movimientos = getMovimientos(cuentaSeleccionada)

                movimientoAdapter = MovimientoAdapter(movimientos)
                binding.reciclerView.adapter = movimientoAdapter
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getMovimientos(cuenta: Cuenta): MutableList<Movimiento> {
        val mbo = MiBancoOperacional.getInstance(this)
        return mbo?.getMovimientos(cuenta) as? MutableList<Movimiento> ?: arrayListOf()
    }
}