package com.example.t3a3_benavent_vicent3.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat
import com.example.t3a3_benavent_vicent3.LoginActivity
import com.example.t3a3_benavent_vicent3.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configurar DrawerLayout
        drawerLayout = findViewById(R.id.main)
        val navView: NavigationView = findViewById(R.id.nav_view)

        // Toggle para abrir y cerrar el Drawer
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Manejar clics en los ítems del NavigationView
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, TransferActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_settings -> {
                    val intent = Intent(this, ChangePasswordActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_share -> {
                    val intent = Intent(this, MovementsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_about -> {
                    val intent = Intent(this, GlobalPositionActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_logout -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_ajustes -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                }

            }
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
