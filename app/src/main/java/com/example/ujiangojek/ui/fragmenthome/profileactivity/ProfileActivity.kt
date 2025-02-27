package com.example.ujiangojek.ui.fragmenthome.profileactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ujiangojek.R
import com.example.ujiangojek.databinding.ActivityProfileBinding
import com.example.ujiangojek.dataclass.ProfileItem
import com.example.ujiangojek.ui.adapter.ProfileAdapter

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarProfile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appBarProfile.setNavigationOnClickListener { onBackPressed() }

        val profileItems = listOf(
            ProfileItem(R.drawable.ic_gocar, "My activity", "See ongoing & history"),
            ProfileItem(R.drawable.ic_gofood, "Gojek PLUS"),
            ProfileItem(R.drawable.ic_profile, "Promos"),
            ProfileItem(R.drawable.ic_gomart, "Payment Methods"),
            ProfileItem(R.drawable.ic_gosend, "Help center"),
            ProfileItem(R.drawable.ic_gopay, "Business Profile"),
            ProfileItem(R.drawable.ic_friend, "Change language"),
            ProfileItem(R.drawable.ic_gofood, "Saved addresses"),
            ProfileItem(R.drawable.ic_gocleaning, "App accessibility"),
            ProfileItem(R.drawable.ic_friend, "Invite and Earn")
        )

        binding.rvProfileList.layoutManager = LinearLayoutManager(this)
        binding.rvProfileList.adapter = ProfileAdapter(profileItems)

        binding.icEdit.setOnClickListener{
            val userProfile = viewModel.profile.value
            if (userProfile != null) {
                val intent = Intent(this, UpdateProfileActivity::class.java)
                intent.putExtra(UpdateProfileActivity.EXTRA_DATA, userProfile)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Data profil tidak tersedia", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.profile.observe(this) { userProfile ->
            if (userProfile != null) {
                Log.d("ProfileActivity", "Nama: ${userProfile.nama}, Email: ${userProfile.email}")

                Glide.with(binding.root.context)
                    .load(userProfile.foto)
                    .into(binding.imgProfile)

                binding.tvProfileName.text = userProfile.nama
                binding.tvProfileEmail.text = userProfile.email
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getProfile()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}
