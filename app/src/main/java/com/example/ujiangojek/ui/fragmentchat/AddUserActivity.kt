package com.example.ujiangojek.ui.fragmentchat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.ujiangojek.MainActivity
import com.example.ujiangojek.R
import com.example.ujiangojek.data.response.ListUserResponseItem
import com.example.ujiangojek.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appBar.setNavigationOnClickListener { onBackPressed() }

        val addViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()).get(AddUserViewModel::class.java
        )

        addViewModel.addResponse.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnAddChat.setOnClickListener {
            val nama = binding.etNama.text
            val email = binding.etEmail.text
            val noTelp = binding.etNoTelp.text
            val linkFoto = binding.etFoto.text

            if (nama?.isEmpty() == true){
                binding.etNama.error = "Field Harus Diisi!!!"
            }else if (email?.isEmpty() == true){
                binding.etEmail.error = "Field Harus Diisi!!!"
            }else if (noTelp?.isEmpty() == true){
                binding.etNoTelp.error = "Field Harus Diisi!!!"
            }else if (linkFoto?.isEmpty() == true){
                binding.etFoto.error = "Field Harus Diisi!!!"
            }else{
                addViewModel.insertSiswa(nama.toString(), noTelp.toString(), email.toString(), linkFoto.toString())
            }
        }

    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}