package com.example.ujiangojek.fragmentchat.icon

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ujiangojek.R
import com.example.ujiangojek.databinding.ActivityInboxBinding

class InboxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInboxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInboxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarInbox)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appBarInbox.setNavigationOnClickListener { onBackPressed() }


    }
}