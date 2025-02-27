package com.example.ujiangojek.ui.fragmentpromo

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
import androidx.recyclerview.widget.RecyclerView
import com.example.ujiangojek.ui.PromoCodeActivity
import com.example.ujiangojek.R
import com.example.ujiangojek.data.response.PosterItem
import com.example.ujiangojek.ui.adapter.ListFoodTopRateAdapter
import com.example.ujiangojek.ui.adapter.MenuPromoAdapter
import com.example.ujiangojek.databinding.FragmentPromoBinding
import com.example.ujiangojek.dataclass.FoodTopRate
import com.example.ujiangojek.dataclass.MenuPromo
import com.example.ujiangojek.ui.adapter.ListPosterAdapter


class PromoFragment : Fragment() {


    private var _binding: FragmentPromoBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvMenu: RecyclerView
    private lateinit var menuPromo: ArrayList<MenuPromo>
    private lateinit var rvFood: RecyclerView
    private lateinit var dataListFood: ArrayList<FoodTopRate>
    private lateinit var promoViewModel: PromoViewModel


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

        val progressBar = binding.progressBar

        val colorIndeterminate = ContextCompat.getColor(requireContext(), R.color.green)
        progressBar.indeterminateTintList = ColorStateList.valueOf(colorIndeterminate)
        progressBar.indeterminateTintMode = PorterDuff.Mode.SRC_ATOP

        // viewmodel untuk poster
        promoViewModel = ViewModelProvider(this).get(PromoViewModel::class.java)

        promoViewModel.listPoster.observe(viewLifecycleOwner){poster ->
            setPosterData(poster)
        }

        promoViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        menuPromo = ArrayList()
        menuPromo.addAll(getListMenu())
        rvMenu = binding.rvMenu


        dataListFood = ArrayList()
        dataListFood.addAll(getListFood())
        rvFood = binding.rvFood
        rvFood.setHasFixedSize(true)


        binding.rvPoster.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        showRecyclerList()


        binding.btnPromoCode.setOnClickListener{
            val intent = Intent(requireContext(), PromoCodeActivity::class.java)
            startActivity(intent)
        }

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

    }

    private fun setPosterData(dataPoster : List<PosterItem>){
        val adapter = ListPosterAdapter()
        adapter.submitList(dataPoster)
        binding.rvPoster.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}