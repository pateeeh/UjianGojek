package com.example.ujiangojek.fragmentchat.icon

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ujiangojek.R
import com.example.ujiangojek.databinding.ActivityHelpTicketsBinding

class HelpTicketsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHelpTicketsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.appBarHelpTickets)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appBarHelpTickets.setNavigationOnClickListener { onBackPressed() }
    }


}