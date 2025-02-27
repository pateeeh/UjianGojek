package com.example.ujiangojek.ui.fragmentactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.ujiangojek.R

import com.example.ujiangojek.databinding.FragmentActivityBinding
import com.example.ujiangojek.ui.fragmentchat.ChatFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ActivityFragment : Fragment() {

    private lateinit var binding: FragmentActivityBinding
    private lateinit var tabLayout: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityBinding.inflate(inflater, container, false)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val intent = requireActivity().intent
        val targetFragment = intent.getStringExtra("NAV_TARGET")

        if (targetFragment == "FragmentActivity") {
            navController.navigate(R.id.nav_activityFragment)
        }
    }
}