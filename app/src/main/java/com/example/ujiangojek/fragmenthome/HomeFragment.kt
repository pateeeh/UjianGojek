package com.example.ujiangojek.fragmenthome

import android.content.Intent
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
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ujiangojek.ProfileActivity
import com.example.ujiangojek.R
import com.example.ujiangojek.adapter.ListFoodTopRateAdapter
import com.example.ujiangojek.adapter.ListMartProdukAdapter
import com.example.ujiangojek.databinding.FragmentHomeBinding
import com.example.ujiangojek.dataclass.FoodTopRate
import com.example.ujiangojek.dataclass.GoMartProduk

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvFood: RecyclerView
    private lateinit var rvProduk: RecyclerView
    private lateinit var dataListFood: ArrayList<FoodTopRate>
    private lateinit var dataListProduk: ArrayList<GoMartProduk>


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

        // Menyiapkan data untuk RecyclerView
        dataListFood = ArrayList()
        dataListFood.addAll(getListFood())
        dataListProduk = ArrayList()
        dataListProduk.addAll(getListProduk())

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

        val listFood = ArrayList<FoodTopRate>()
        for (i in dataName.indices) {
            val food = FoodTopRate(dataPhoto.getResourceId(i, -1), dataName[i])
            listFood.add(food)
        }
        dataPhoto.recycle()
        return listFood
    }

    private fun getListProduk(): ArrayList<GoMartProduk> {
        val dataPhoto = resources.obtainTypedArray(R.array.data_img_gomart)
        val dataName = resources.getStringArray(R.array.data_produk_gomart)
        val dataHarga = resources.getStringArray(R.array.data_harga_gomart)

        val listProduk = ArrayList<GoMartProduk>()
        for (i in dataName.indices) {
            val produk = GoMartProduk(dataPhoto.getResourceId(i, -1), dataName[i], dataHarga[i])
            listProduk.add(produk)
        }
        dataPhoto.recycle()
        return listProduk
    }

    private fun showRecyclerList() {
        rvFood.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listFoodAdapter = ListFoodTopRateAdapter(dataListFood)
        rvFood.adapter = listFoodAdapter

        rvProduk.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listMartProdukAdapter = ListMartProdukAdapter(dataListProduk)
        rvProduk.adapter = listMartProdukAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
