package com.example.t3a3_benavent_vicent3.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_benavent_vicent3.R
import com.example.t3a3_benavent_vicent3.databinding.ActivityAtmManagmentBinding

class AtmManagmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtmManagmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmManagmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.botonLista.setOnClickListener {
            val intent = Intent(this, AtmListActivity::class.java)
            startActivity(intent)
        }
        binding.botonAnadir.setOnClickListener {
            val intent = Intent(this, AtmFormActivity::class.java)
            startActivity(intent)
        }
    }
}