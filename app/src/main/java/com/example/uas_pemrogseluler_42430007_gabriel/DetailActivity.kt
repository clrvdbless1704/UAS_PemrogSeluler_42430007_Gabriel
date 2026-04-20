package com.example.uas_pemrogseluler_42430007_gabriel

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nama = intent.getStringExtra("EXTRA_NAMA")
        val harga = intent.getIntExtra("EXTRA_HARGA", 0)
        val desc = intent.getStringExtra("EXTRA_DESC")
        val foto = intent.getIntExtra("EXTRA_FOTO", 0)

        val ivFoto = findViewById<ImageView>(R.id.ivDetailFoto)
        val tvNama = findViewById<TextView>(R.id.tvDetailNama)
        val tvHarga = findViewById<TextView>(R.id.tvDetailHarga)
        val tvDesc = findViewById<TextView>(R.id.tvDetailDesc)

        ivFoto.setImageResource(foto)
        tvNama.text = nama
        tvHarga.text = getString(R.string.detail_harga, harga)
        tvDesc.text = desc
    }
}
