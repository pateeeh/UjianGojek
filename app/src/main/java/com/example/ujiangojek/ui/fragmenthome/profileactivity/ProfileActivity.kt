package com.example.ujiangojek.ui.fragmenthome.profileactivity

import android.os.Bundle
import android.util.Log
import android.view.View
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

        // ✅ Observe profile LiveData
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

        // ✅ Observe isLoading untuk progress bar
        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        // ✅ Panggil API setelah observer dipasang
        viewModel.getProfile()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
