package com.example.t3a3_benavent_vicent3.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.example.bancoapiprofe.pojo.Cliente
import com.example.bancoapiprofe.pojo.Cuenta
import com.example.t3a3_benavent_vicent3.R
import com.example.t3a3_benavent_vicent3.databinding.ActivityGlobalPositionBinding

class GlobalPositionActivity : AppCompatActivity() {

    private lateinit var cuentaAdapter: CuentaAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityGlobalPositionBinding

    private var cliente: Cliente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cliente = intent.getSerializableExtra("Cliente") as? Cliente

        cliente?.let {
            cuentaAdapter = CuentaAdapter(getCuentas(it))
            linearLayoutManager = LinearLayoutManager(this)

            binding.reciclerView.apply {
                layoutManager = linearLayoutManager
                adapter = cuentaAdapter
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getCuentas(cliente: Cliente): MutableList<Cuenta> {
        val mbo = MiBancoOperacional.getInstance(this)
        val cuentas = mbo?.getCuentas(cliente) as? ArrayList<Cuenta> ?: arrayListOf()

        return cuentas
    }
}