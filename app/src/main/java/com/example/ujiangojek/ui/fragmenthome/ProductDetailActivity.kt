package com.example.ujiangojek.ui.fragmenthome

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.ujiangojek.MainActivity
import com.example.ujiangojek.R
import com.example.ujiangojek.data.response.ProdukItem
import com.example.ujiangojek.databinding.ActivityProductDetailBinding
import com.example.ujiangojek.ui.fragmentactivity.ActivityFragment

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val produkData: ProdukItem? = intent.getParcelableExtra("PRODUK_DATA")

        if (produkData != null) {
            binding.tvProduct.text = produkData.namaProduk
            binding.tvHarga.text = produkData.harga
            binding.tvDesc.text = produkData.deskripsi
            Glide.with(this).load(produkData.gambar).into(binding.imgProduct)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnPesan.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("NAV_TARGET", "FragmentActivity")
            startActivity(intent)
        }
    }

}
