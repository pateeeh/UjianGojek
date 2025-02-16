package com.example.ujiangojek.fragmentpromo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ujiangojek.PromoCodeActivity
import com.example.ujiangojek.R
import com.example.ujiangojek.adapter.ListFoodTopRateAdapter
import com.example.ujiangojek.adapter.MenuPromoAdapter
import com.example.ujiangojek.databinding.FragmentHomeBinding
import com.example.ujiangojek.databinding.FragmentPromoBinding
import com.example.ujiangojek.dataclass.FoodTopRate
import com.example.ujiangojek.dataclass.MenuPromo
import com.example.ujiangojek.dataclass.Poster


class PromoFragment : Fragment() {


    private var _binding: FragmentPromoBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvMenu: RecyclerView
    private lateinit var menuPromo: ArrayList<MenuPromo>
    private lateinit var rvFood: RecyclerView
    private lateinit var dataListFood: ArrayList<FoodTopRate>
    private lateinit var dataPoster: ArrayList<Poster>
    private lateinit var rvPoster: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPromoBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getListMenu(): ArrayList<MenuPromo> {
        val namaMenu = resources.getStringArray(R.array.data_menu)

        val listMenu = ArrayList<MenuPromo>()
        for (i in namaMenu.indices) {
            val menu = MenuPromo(namaMenu[i])
            listMenu.add(menu)
        }
        return listMenu
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuPromo = ArrayList()
        menuPromo.addAll(getListMenu())
        rvMenu = binding.rvMenu


        dataListFood = ArrayList()
        dataListFood.addAll(getListFood())
        rvFood = binding.rvFood
        rvFood.setHasFixedSize(true)

        rvPoster = binding.rvPoster
        dataPoster = ArrayList()
        dataPoster.addAll(getListPoster())


        showRecyclerList()
        val toolbar = view.findViewById<Toolbar>(R.id.appBarPromo)
        (toolbar.context as? AppCompatActivity)?.setSupportActionBar(toolbar)
        (toolbar.context as? AppCompatActivity)?.supportActionBar?.title = ""


        binding.btnPromoCode.setOnClickListener{
            val intent = Intent(requireContext(), PromoCodeActivity::class.java)
            startActivity(intent)
        }


    }

    private fun getListPoster(): Collection<Poster> {
        val typedArray = resources.obtainTypedArray(R.array.data_poster)
        val listPoster = ArrayList<Poster>()

        for (i in 0 until typedArray.length()) {
            val poster = Poster(typedArray.getResourceId(i, -1))
            listPoster.add(poster)
        }
        typedArray.recycle()
        return listPoster
    }

    private fun getListFood(): Collection<FoodTopRate> {
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
        rvMenu.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listMenu = MenuPromoAdapter(menuPromo)
        rvMenu.adapter = listMenu

        rvFood.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listFoodAdapter = ListFoodTopRateAdapter(dataListFood)
        rvFood.adapter = listFoodAdapter

        rvPoster.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listPosterAdapter = com.example.ujiangojek.adapter.ListPosterAdapter(dataPoster)
        rvPoster.adapter = listPosterAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}