package com.example.ujiangojek

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ujiangojek.databinding.ActivityMainBinding
import com.example.ujiangojek.ui.fragmentactivity.ActivityFragment
import com.example.ujiangojek.ui.fragmenthome.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = binding.bottomNavigaton

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val targetFragment = intent.getStringExtra("NAV_TARGET")

        if (targetFragment == "FragmentActivity") {
            navController.navigate(R.id.nav_activityFragment)
        } else {
            // Jika tidak ada target, tetap ke home sebagai default
            navController.navigate(R.id.nav_homeFragment)
        }

        if (supportActionBar != null) {
            setupActionBarWithNavController(navController)
        }
        navView.setupWithNavController(navController)
        }


}


