package com.example.ujiangojek.foodactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ujiangojek.databinding.ActivityFoodDetailBinding

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val banner = binding.foodBanner
        val logo = binding.logo

        val imageResourceId = intent.getIntExtra("KEY_IMAGE", 0)
        val namaToko = intent.getStringExtra("KEY_NAMATOKO")
        val logoResourceId = intent.getIntExtra("KEY_LOGO", 0)

        banner.setImageResource(imageResourceId)
        logo.setImageResource(logoResourceId)

        if (namaToko != null) {
            binding.tvNamaToko.text = namaToko
        }

        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.btnShare.setOnClickListener{
            val shareIntent = Intent()
            shareIntent.action= Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, namaToko)

            startActivity(Intent.createChooser(shareIntent, namaToko))
        }
    }
}