package com.example.t3a3_benavent_vicent3.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_benavent_vicent3.R
import com.example.t3a3_benavent_vicent3.databinding.ActivityAtmFormBinding
import com.example.t3a3_benavent_vicent3.databinding.ActivityAtmListBinding
import com.example.t3a3_benavent_vicent3.pojo.CajeroEntity

class AtmFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAtmFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.title = "Gestion Cajero"

        val cajeroEnviado = intent.getSerializableExtra("cajero") as? CajeroEntity


        if (cajeroEnviado == null) {
            binding.textoLatitud.setText("")
            binding.textoLongitud.setText("")
            binding.textoDireccion.setText("")

        } else {
            binding.textoLatitud.setText(cajeroEnviado?.latitud.toString())
            binding.textoLongitud.setText(cajeroEnviado?.longitud.toString())
            binding.textoDireccion.setText(cajeroEnviado?.direccion)
        }

        binding.botonGuardar.setOnClickListener {
            val cajero = CajeroEntity(binding.textoDireccion.text.toString(),
                                        binding.textoLatitud.text.toString().toDouble(),
                                        binding.textoLongitud.text.toString().toDouble(),
                                        "")

            Thread {
                CajeroApplication.database.cajerosDao().addCajero(cajero)
            }.start()

            val intent = Intent(this, AtmListActivity::class.java)
            startActivity(intent)
        }

        binding.botonCancelar.setOnClickListener {
            val intent = Intent(this, AtmManagmentActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_atm, menu)
        return true
    }

    // Manejar la selección de ítems del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                showDeleteConfirmationDialog()
                true
            }

            R.id.action_update -> {
                val cajeroEnviado = intent.getSerializableExtra("cajero") as? CajeroEntity
                if (cajeroEnviado != null) {
                    // Crear un nuevo objeto Cajero con los datos actualizados
                    val updatedCajero = CajeroEntity(
                        id = cajeroEnviado.id,  // Usar el ID original
                        direccion = binding.textoDireccion.text.toString().trim(),
                        latitud = binding.textoLatitud.text.toString().toDouble(),
                        longitud = binding.textoLongitud.text.toString().toDouble(),
                        ""
                    )

                    // Hacer la actualización en un hilo
                    Thread {
                        CajeroApplication.database.cajerosDao().updateCajero(updatedCajero)
                    }.start()

                    // Mostrar un mensaje de confirmación
                    Toast.makeText(this, "Cajero actualizado correctamente", Toast.LENGTH_SHORT).show()

                    // Regresar a la pantalla principal (o la lista de cajeros)
                    val intent = Intent(this, AtmListActivity::class.java)
                    startActivity(intent)
                    finish()  // Termina esta actividad para no mantenerla en la pila
                } else {
                    Toast.makeText(this, "Cajero no encontrado", Toast.LENGTH_SHORT).show()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteConfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("¿Estás seguro de que deseas eliminar este cajero?")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                val cajeroEnviado = intent.getSerializableExtra("Cajero") as? CajeroEntity
                Thread {
                    if (cajeroEnviado != null) {
                        CajeroApplication.database.cajerosDao().deleteCajero(cajeroEnviado)
                    }
                }.start()
                val intent = Intent(this, AtmListActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                dialog.dismiss()
            }
        val alert = dialogBuilder.create()
        alert.show()
    }
}