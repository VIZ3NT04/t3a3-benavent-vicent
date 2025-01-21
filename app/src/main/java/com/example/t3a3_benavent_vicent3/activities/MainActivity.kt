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
            setSupportActionBar(toolbar) // Usar Toolbar como Action Bar

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
                    R.id.nav_home -> startActivity(Intent(this, TransferActivity::class.java))
                    R.id.nav_settings -> startActivity(Intent(this, ChangePasswordActivity::class.java))
                    R.id.nav_share -> startActivity(Intent(this, MovementsActivity::class.java))
                    R.id.nav_about -> startActivity(Intent(this, GlobalPositionActivity::class.java))
                    R.id.nav_logout -> startActivity(Intent(this, LoginActivity::class.java))
                    R.id.nav_ajustes -> startActivity(Intent(this, SettingsActivity::class.java))
                    R.id.nav_cajeros -> startActivity(Intent(this, AtmManagmentActivity::class.java))
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
