package com.example.uas_pemrogseluler_42430007_gabriel

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_detail)
            supportActionBar?.hide()

            val nama = intent.getStringExtra("NAMA")
            val harga = intent.getIntExtra("HARGA", 0)
            val desc = intent.getStringExtra("DESC")
            val proc = intent.getStringExtra("PROCESSOR")
            val ram = intent.getStringExtra("RAM")
            val storage = intent.getStringExtra("STORAGE")
            val gpu = intent.getStringExtra("GRAPHICS")
            val display = intent.getStringExtra("DISPLAY")
            val foto = intent.getIntExtra("FOTO", 0)

            findViewById<ImageView>(R.id.ivDetailFoto).setImageResource(foto)
            findViewById<TextView>(R.id.tvDetailNama).text = nama
            findViewById<TextView>(R.id.tvDetailDesc).text = desc

            findViewById<TextView>(R.id.tvDetailProc).text = getString(R.string.spec_processor, proc)
            findViewById<TextView>(R.id.tvDetailRam).text = getString(R.string.spec_ram, ram)
            findViewById<TextView>(R.id.tvDetailStorage).text = getString(R.string.spec_storage, storage)
            findViewById<TextView>(R.id.tvDetailGpu).text = getString(R.string.spec_graphics, gpu)
            findViewById<TextView>(R.id.tvDetailDisplay).text = getString(R.string.spec_display, display)

            val localeID = Locale.Builder().setLanguage("in").setRegion("ID").build()
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            findViewById<TextView>(R.id.tvDetailHarga).text = getString(R.string.detail_harga, formatRupiah.format(harga))

        } catch (e: Exception) {
            Log.e("42430007", "Error Detail: ${e.message}")
        }
    }
}
