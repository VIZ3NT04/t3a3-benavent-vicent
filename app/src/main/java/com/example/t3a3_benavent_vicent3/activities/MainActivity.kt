package com.example.t3a3_benavent_vicent3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bancoapiprofe.pojo.Cliente
import com.example.t3a3_benavent_vicent3.activities.ChangePasswordActivity
import com.example.t3a3_benavent_vicent3.activities.GlobalPositionActivity
import com.example.t3a3_benavent_vicent3.activities.MovementsActivity
import com.example.t3a3_benavent_vicent3.activities.TransferActivity
import com.example.t3a3_benavent_vicent3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val cliente = intent.getSerializableExtra("Cliente") as Cliente

        binding.benvingut.text = "Benvingut \n ${cliente.getNombre()}"

        binding.btnEntrarBanco7.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnEntrarBanco4.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        binding.btnEntrarBanco3.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            //intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }
        binding.btnEntrarBanco1.setOnClickListener {
            val intent = Intent(this, GlobalPositionActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        binding.btnEntrarBanco2.setOnClickListener {
            val intent = Intent(this, MovementsActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}
