package com.example.t3a3_benavent_vicent3.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.t3a3_benavent_vicent3.R
import com.google.android.material.button.MaterialButton

class TransferActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transfer)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val Cuenta: Spinner = findViewById(R.id.spCuenta)
        val opcion1: Spinner = findViewById(R.id.spCuentaPropia)
        val opcion2: EditText = findViewById(R.id.etCantidadAjena)
        val radioGroup: RadioGroup = findViewById(R.id.rgTipoCuenta)
        val etCantidad: EditText = findViewById(R.id.etCantidad)
        val checkbox: CheckBox = findViewById(R.id.cbEnviarJustificante)
        val btEnviar: MaterialButton = findViewById(R.id.btEnviar)
        val btCancelar: MaterialButton = findViewById(R.id.btCancelar)

        // Configuración del Spinner
        val cuentas = resources.getStringArray(R.array.cuentas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentas)
        Cuenta.adapter = adapter

        Cuenta.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val opcionSeleccionada = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@TransferActivity, "Seleccionaste $opcionSeleccionada", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada
            }
        }

        // Manejo del RadioGroup
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rdCuentaPropia -> {
                    opcion1.visibility = View.VISIBLE
                    opcion2.visibility = View.GONE
                }
                R.id.rdCuentaAjena -> {
                    opcion1.visibility = View.GONE
                    opcion2.visibility = View.VISIBLE
                }
            }
        }

        // Botón Cancelar
        btCancelar.setOnClickListener {
            Cuenta.setSelection(0)
            opcion1.setSelection(0)
            opcion2.setText("")
            etCantidad.setText("")
            radioGroup.clearCheck()
            checkbox.isChecked = false
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Botón Enviar
        btEnviar.setOnClickListener {
            val cuentaSeleccionada = Cuenta.selectedItem.toString()
            val cantidad = etCantidad.text.toString()
            val tipoCuenta = when (radioGroup.checkedRadioButtonId) {
                R.id.rdCuentaPropia -> "A cuenta propia"
                R.id.rdCuentaAjena -> "A cuenta ajena"
                else -> ""
            }
            val cuentaAjena = opcion2.text.toString()
            val justificante = if (checkbox.isChecked) "Enviar justificante" else ""

            Toast.makeText(
                this@TransferActivity,
                "Cuenta Origen: $cuentaSeleccionada\nTipo de Cuenta: $tipoCuenta\nCantidad: $cantidad\nCuenta Ajena: $cuentaAjena\n$justificante",
                Toast.LENGTH_LONG
            ).show()


        }
    }
}

