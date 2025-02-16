package com.example.ujiangojek.fragmentchat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ujiangojek.R
import com.example.ujiangojek.databinding.FragmentActivityBinding
import com.example.ujiangojek.databinding.FragmentChatBinding
import com.example.ujiangojek.fragmentchat.icon.HelpTicketsActivity
import com.example.ujiangojek.fragmentchat.icon.InboxActivity

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

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
    }
}