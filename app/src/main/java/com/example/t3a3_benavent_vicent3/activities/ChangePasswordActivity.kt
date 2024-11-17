package com.example.t3a3_benavent_vicent3.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.example.bancoapiprofe.pojo.Cliente
import com.example.t3a3_benavent_vicent3.LoginActivity
import com.example.t3a3_benavent_vicent3.MainActivity
import com.example.t3a3_benavent_vicent3.R
import com.example.t3a3_benavent_vicent3.databinding.ActivityChangePasswordBinding
import com.example.t3a3_benavent_vicent3.databinding.ActivityMainBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        binding.btCambiarPass.setOnClickListener {
            val newPass = binding.textFieldPass.text.toString()
            val confirmPass = binding.textFieldDNI.text.toString()

            if (newPass == confirmPass) {
                cliente.setClaveSeguridad(newPass)

                // Llama a la función changePassword para actualizar en la BD
                val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
                val resultado = mbo?.changePassword(cliente)
                if (resultado == 1) {


                    Toast.makeText(this, "Se ha cambiado la contraseña correctamente", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Error al cambiar la contraseña", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            }
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
