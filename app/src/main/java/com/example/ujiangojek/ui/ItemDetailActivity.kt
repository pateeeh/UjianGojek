package com.example.ujiangojek.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ujiangojek.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = binding.cardImage

        binding.btnBack.setOnClickListener{
            finish()
        }
    }
}