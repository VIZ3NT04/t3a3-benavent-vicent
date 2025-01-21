package com.example.t3a3_benavent_vicent3.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t3a3_benavent_vicent3.R
import com.example.t3a3_benavent_vicent3.databinding.ActivityAtmListBinding
import com.example.t3a3_benavent_vicent3.pojo.CajeroEntity

class AtmListActivity : AppCompatActivity(), onClickListener {
    private lateinit var binding: ActivityAtmListBinding
    private lateinit var mAdapter: CajeroAdapter
    private lateinit var mGridLayout: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        val cajerosEntityLists : List<CajeroEntity> = listOf(
            CajeroEntity( "Carrer del Clariano, 1, 46021 Valencia, Valencia, España", 39.47600769999999, -0.3524475000000393, ""),
            CajeroEntity( "Avinguda del Cardenal Benlloch, 65, 46021 València, Valencia, España", 39.4710366, -0.3547525000000178,"" ),
            CajeroEntity( "Av. del Port, 237, 46011 València, Valencia, España", 39.46161999999999, -0.3376299999999901, ""),
            CajeroEntity( "Carrer del Batxiller, 6, 46010 València, Valencia, España", 39.4826729, -0.3639118999999482, ""),
            CajeroEntity( "Av. del Regne de València, 2, 46005 València, Valencia, España", 39.4647669, -0.3732760000000326, ""))
        Thread {
            val cajeroDao = CajeroApplication.database.cajerosDao()
            val cajerosExistentes = cajeroDao.getAllCajeros()
            if (cajerosExistentes.isEmpty()) {
                cajerosEntityLists.forEach {
                    cajeroDao.addCajero(it)
                }

            }

            val cajerosFromDb = CajeroApplication.database.cajerosDao().getAllCajeros()
            runOnUiThread {
               mAdapter.add(cajerosFromDb)
               mAdapter.notifyDataSetChanged()
            }
        }.start()



    }

    private fun setupRecyclerView() {
        mAdapter = CajeroAdapter(mutableListOf(),this)
        mGridLayout = LinearLayoutManager(this)
        binding.reciclerView.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter

        }

    }

    override fun onClick(cajeroEntity: CajeroEntity) {
        val intent = Intent(this, AtmFormActivity::class.java)
        intent.putExtra("cajero", cajeroEntity)
        startActivity(intent)
    }
}