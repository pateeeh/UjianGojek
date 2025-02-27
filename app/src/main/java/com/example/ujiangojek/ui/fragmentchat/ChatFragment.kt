package com.example.ujiangojek.ui.fragmentchat

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujiangojek.R
import com.example.ujiangojek.data.response.ListUserResponseItem
import com.example.ujiangojek.databinding.FragmentActivityBinding
import com.example.ujiangojek.databinding.FragmentChatBinding
import com.example.ujiangojek.ui.adapter.ListChatAdapter
import com.example.ujiangojek.ui.fragmentchat.icon.HelpTicketsActivity
import com.example.ujiangojek.ui.fragmentchat.icon.InboxActivity

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var chatViewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return (binding.root)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icInbox.setOnClickListener{
            val intent = Intent(requireContext(), InboxActivity::class.java)
            startActivity(intent)
        }

        binding.icHelpTickets.setOnClickListener{
            val intent = Intent(requireContext(), HelpTicketsActivity::class.java)
            startActivity(intent)
        }

        val progressBar = binding.progressBar

        val colorIndeterminate = ContextCompat.getColor(requireContext(), R.color.green)
        progressBar.indeterminateTintList = ColorStateList.valueOf(colorIndeterminate)
        progressBar.indeterminateTintMode = PorterDuff.Mode.SRC_ATOP

        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        chatViewModel.chatList.observe(viewLifecycleOwner){chat ->
            setChatData(chat)
        }

        chatViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        binding.rvChat.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }

    private fun setChatData(dataChat: List<ListUserResponseItem>?) {
        val adapter = ListChatAdapter()
        adapter.submitList(dataChat)
        binding.rvChat.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}