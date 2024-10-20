package com.example.t3a3_benavent_vicent3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_benavent_vicent3.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btEntrar.setOnClickListener {
            // Obtener los textos de usuario y contraseña de forma segura
            val usuario = binding.textFieldDNI.text.toString().trim()
            val contrasena = binding.textFieldPass.text.toString().trim()

            // Validar campos
            if (usuario.isNullOrEmpty()) {
                binding.layaoutDNI.error = "El campo DNI está vacío"
            } else {
                binding.layaoutDNI.error = null
            }

            if (contrasena.isNullOrEmpty()) {
                binding.layaoutPass.error = "El campo contraseña está vacío"
            } else {
                binding.layaoutPass.error = null
            }

            // Si ambos campos están completos, inicia la MainActivity
            if (!usuario.isNullOrEmpty() && !contrasena.isNullOrEmpty()) {

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("Usuario", usuario)  // Pasa el usuario
                    startActivity(intent)
            }
        }
        binding.btSalir.setOnClickListener {
            finishAffinity()
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



    }
}
