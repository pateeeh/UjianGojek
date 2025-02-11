package com.example.ujiangojek

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujiangojek.adapter.ProfileAdapter
import com.example.ujiangojek.databinding.ActivityProfileBinding
import com.example.ujiangojek.dataclass.ProfileItem

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

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

    }

}
