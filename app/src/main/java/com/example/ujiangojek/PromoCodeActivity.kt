package com.example.ujiangojek

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.ujiangojek.databinding.ActivityProfileBinding
import com.example.ujiangojek.databinding.ActivityPromoCodeBinding

class PromoCodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPromoCodeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromoCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarPromoCode)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appBarPromoCode.setNavigationOnClickListener { onBackPressed() }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }else->
                return super.onOptionsItemSelected(item)
            }
        }



    }





