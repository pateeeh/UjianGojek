package com.example.ujiangojek.ui.fragmenthome.profileactivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ujiangojek.R
import com.example.ujiangojek.data.response.ListUserResponseItem
import com.example.ujiangojek.databinding.ActivityUpdateProfileBinding

class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProfileBinding
    private var id:Int = 0

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val updateViewModel = ViewModelProvider(this)[UpdateViewModel::class.java]

        binding.icBack.setOnClickListener {
            finish()
        }

        val user = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, ListUserResponseItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if(user!=null){
            id = user.id!!
            binding.etNama.setText(user.nama)
            binding.etPhoneNumber.setText(user.noTelp)
            binding.etEmail.setText(user.email)

            Glide.with(this)
                .load(user.foto)
                .into(binding.circleImageView)
        }

        binding.btnSave.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val noTelp = binding.etPhoneNumber.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            if (nama.isEmpty() || noTelp.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            updateViewModel.updateUserProfile(id, nama, noTelp, email)

        }

        updateViewModel.updateResponse.observe(this) {message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
        }

        updateViewModel.isLoading.observe(this) {
            showLoading(it)
        }


    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}