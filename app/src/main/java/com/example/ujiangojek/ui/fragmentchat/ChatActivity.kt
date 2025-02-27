package com.example.ujiangojek.ui.fragmentchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.ujiangojek.data.response.ListUserResponseItem
import com.example.ujiangojek.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarChat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appBarChat.setNavigationOnClickListener { onBackPressed() }

        val userData: ListUserResponseItem? = intent.getParcelableExtra("USER_DATA")

        if (userData != null) {
            binding.tvNama.text = userData.nama
            Glide.with(this).load(userData.foto).into(binding.imgProfile)
        }

    }
}