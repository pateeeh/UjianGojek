package com.example.ujiangojek.ui.fragmenthome

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ujiangojek.ui.fragmenthome.profileactivity.ProfileActivity
import com.example.ujiangojek.R
import com.example.ujiangojek.data.response.ProdukItem
import com.example.ujiangojek.ui.adapter.ListFoodTopRateAdapter
import com.example.ujiangojek.ui.adapter.ListMartProdukAdapter
import com.example.ujiangojek.databinding.FragmentHomeBinding
import com.example.ujiangojek.dataclass.FoodTopRate
import com.example.ujiangojek.dataclass.GoMartProduk

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvFood: RecyclerView
    private lateinit var rvProduk: RecyclerView
    private lateinit var dataListFood: ArrayList<FoodTopRate>
    private lateinit var listProdukAdapter: ListMartProdukAdapter
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menjadikan toolbar sebagai ActionBar
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.topAppbar)

        binding.topAppbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ic_profile -> {
                    startActivity(Intent(requireContext(), ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }

        val progressBar = binding.progressBar

        val colorIndeterminate = ContextCompat.getColor(requireContext(), R.color.green)
        progressBar.indeterminateTintList = ColorStateList.valueOf(colorIndeterminate)
        progressBar.indeterminateTintMode = PorterDuff.Mode.SRC_ATOP

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.listProduk.observe(viewLifecycleOwner){
            setProdukData(it)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        listProdukAdapter = ListMartProdukAdapter()
        binding.rvGoMart.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvGoMart.adapter = listProdukAdapter

        // Menyiapkan data untuk RecyclerView
        dataListFood = ArrayList()
        dataListFood.addAll(getListFood())

        rvFood = binding.rvFood
        rvFood.setHasFixedSize(true)
        rvProduk = binding.rvGoMart
        rvProduk.setHasFixedSize(true)

        showRecyclerList()


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(requireContext(), "Mencari: $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setProdukData(dataProduk: List<ProdukItem>){
        listProdukAdapter.submitList(dataProduk)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // Menangani klik menu di AppBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ic_profile -> {
                startActivity(Intent(requireContext(), ProfileActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListFood(): ArrayList<FoodTopRate> {
        val dataPhoto = resources.obtainTypedArray(R.array.data_img_food)
        val dataName = resources.getStringArray(R.array.data_toko)
        val dataLogo = resources.obtainTypedArray(R.array.data_logo_food)

        val listFood = ArrayList<FoodTopRate>()
        for (i in dataName.indices) {
            val food = FoodTopRate(dataPhoto.getResourceId(i, -1), dataName[i], dataLogo.getResourceId(i, 0))
            listFood.add(food)
        }
        dataPhoto.recycle()
        return listFood
    }


    private fun showRecyclerList() {
        rvFood.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listFoodAdapter = ListFoodTopRateAdapter(dataListFood)
        rvFood.adapter = listFoodAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
