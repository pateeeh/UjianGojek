package com.example.ujiangojek.ui.fragmentchat.icon

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujiangojek.R
import com.example.ujiangojek.data.response.ListInboxResponseItem
import com.example.ujiangojek.databinding.ActivityInboxBinding
import com.example.ujiangojek.ui.adapter.InboxAdapter

class InboxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInboxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInboxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarInbox)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appBarInbox.setNavigationOnClickListener { onBackPressed() }

        val layoutManager = LinearLayoutManager(this)
        binding.rvInbox.layoutManager = layoutManager
        val itemDecoration = androidx.recyclerview.widget.DividerItemDecoration(this, layoutManager.orientation)
        binding.rvInbox.addItemDecoration(itemDecoration)

        val inboxViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(InboxViewModel::class.java)

        inboxViewModel.listInbox.observe(this){
            setInboxData(it)
        }

        inboxViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun setInboxData(dataInbox: List<ListInboxResponseItem>) {
        val adapter = InboxAdapter()
        adapter.submitList(dataInbox)
        binding.rvInbox.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}