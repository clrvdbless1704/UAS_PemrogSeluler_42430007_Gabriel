package com.example.uas_pemrogseluler_42430007_gabriel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.*

data class Laptop(
    val nama: String,
    val harga: Int,
    val desc: String,
    val processor: String,
    val ram: String,
    val storage: String,
    val graphics: String,
    val display: String,
    val foto: Int
)

class MainActivity : AppCompatActivity() {
    private val nimTag = "42430007"
    private var listTampil = mutableListOf<Laptop>()
    private lateinit var adapter: LaptopAdapter
    val daftarLaptop = listOf(
        Laptop("Lenovo Chromebook Flex", 5500000, "Chromebook fleksibel.",
            "Intel Celeron N4020", "4GB", "64GB eMMC", "Intel UHD", "11.6\" HD", R.drawable.lenovo_chromebook_flex),
        Laptop("Lenovo IdeaPad Chromebook", 6000000, "Laptop ChromeOS simpel.",
            "MediaTek Kompanio 520", "4GB", "64GB eMMC", "Mali-G52", "14\" HD", R.drawable.lenovo_ideapad_chromebook),
        Laptop("Lenovo IdeaPad Flex", 9500000, "2-in-1 touchscreen.",
            "Intel Core i3-1115G4", "8GB", "256GB SSD", "Intel UHD", "14\" FHD Touch", R.drawable.lenovo_ideapad_flex),
        Laptop("Lenovo IdeaPad Gaming", 12500000, "Gaming entry.",
            "Ryzen 5 5600H", "8GB", "512GB SSD", "GTX 1650", "15.6\" FHD", R.drawable.lenovo_ideapad_gaming),
        Laptop("Lenovo IdeaPad Slim 1", 4500000, "Basic laptop.",
            "AMD A6", "4GB", "256GB SSD", "Radeon", "14\" HD", R.drawable.lenovo_ideapad_slim_1),
        Laptop("Lenovo IdeaPad Slim 3", 7500000, "Laptop kuliah.",
            "Ryzen 3 5300U", "8GB", "512GB SSD", "Radeon Vega", "15.6\" FHD", R.drawable.lenovo_ideapad_slim_3),
        Laptop("Lenovo IdeaPad Slim 5", 10500000, "Tipis kencang.",
            "Intel i5-1235U", "16GB", "512GB SSD", "Intel Iris Xe", "14\" FHD", R.drawable.lenovo_ideapad_slim_5),
        Laptop("Lenovo Legion 5", 18500000, "Gaming RTX.",
            "Ryzen 7 5800H", "16GB", "512GB SSD", "RTX 3060", "15.6\" FHD 165Hz", R.drawable.lenovo_legion_5),
        Laptop("Lenovo Legion 5 Pro", 23000000, "Gaming high-end.",
            "Ryzen 7 6800H", "16GB", "1TB SSD", "RTX 3070", "16\" WQXGA 165Hz", R.drawable.lenovo_legion_5_pro),
        Laptop("Lenovo Legion 7", 28000000, "Flagship gaming.",
            "Ryzen 9 6900HX", "32GB", "1TB SSD", "RTX 3080", "16\" QHD 165Hz", R.drawable.lenovo_legion_7),
        Laptop("Lenovo LOQ 16", 15500000, "Gaming baru.",
            "Intel i5-13420H", "16GB", "512GB SSD", "RTX 4050", "16\" FHD", R.drawable.lenovo_loq_16),
        Laptop("Lenovo ThinkBook 14", 11000000, "Bisnis ringan.",
            "Intel i5-1235U", "8GB", "512GB SSD", "Intel Iris Xe", "14\" FHD", R.drawable.lenovo_thinkbook_14),
        Laptop("Lenovo ThinkBook 15", 12000000, "Produktivitas.",
            "Intel i5-1235U", "8GB", "512GB SSD", "Intel Iris Xe", "15.6\" FHD", R.drawable.lenovo_thinkbook_15),
        Laptop("Lenovo ThinkBook 16", 13500000, "Layar luas.",
            "Intel i5-13420H", "16GB", "512GB SSD", "Intel UHD", "16\" FHD", R.drawable.lenovo_thinkbook_16),
        Laptop("Lenovo ThinkPad E Series", 14000000, "ThinkPad entry.",
            "Intel i5-1235U", "8GB", "512GB SSD", "Intel Iris Xe", "14\" FHD", R.drawable.lenovo_thinkpad_e_series),
        Laptop("Lenovo ThinkPad L Series", 15500000, "Durable bisnis.",
            "Intel i5-1245U", "16GB", "512GB SSD", "Intel Iris Xe", "14\" FHD", R.drawable.lenovo_thinkpad_l_series),
        Laptop("Lenovo ThinkPad P Series", 30000000, "Workstation.",
            "Intel i7-12700H", "32GB", "1TB SSD", "RTX A2000", "15.6\" FHD", R.drawable.lenovo_thinkpad_p_series),
        Laptop("Lenovo ThinkPad T Series", 22000000, "Premium bisnis.",
            "Intel i7-1260P", "16GB", "512GB SSD", "Intel Iris Xe", "14\" FHD", R.drawable.lenovo_thinkpad_t_series),
        Laptop("Lenovo ThinkPad X Series", 25000000, "Ultra ringan.",
            "Intel i7-1260P", "16GB", "1TB SSD", "Intel Iris Xe", "13.3\" FHD", R.drawable.lenovo_thinkpad_x_series),
        Laptop("Lenovo Yoga 7", 16000000, "2-in-1 premium.",
            "Intel i5-1240P", "16GB", "512GB SSD", "Intel Iris Xe", "14\" Touch", R.drawable.lenovo_yoga_7),
        Laptop("Lenovo Yoga 9", 21000000, "High-end convertible.",
            "Intel i7-1260P", "16GB", "1TB SSD", "Intel Iris Xe", "14\" OLED", R.drawable.lenovo_yoga_9),
        Laptop("Lenovo Yoga Duet", 14000000, "Detachable.",
            "Intel i5-1135G7", "8GB", "512GB SSD", "Intel Iris Xe", "13\" Touch", R.drawable.lenovo_yoga_duet),
        Laptop("Lenovo Yoga Slim", 17000000, "Tipis premium.",
            "Intel i7-1260P", "16GB", "1TB SSD", "Intel Iris Xe", "14\" OLED", R.drawable.lenovo_yoga_slim),
        Laptop("Lenovo LOQ 15", 14500000, "Gaming mid.",
            "Intel i5-13420H", "16GB", "512GB SSD", "RTX 4050", "15.6\" FHD", R.drawable.lenovo_loq_15)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_main)
            supportActionBar?.hide()
            Log.d(nimTag, "Aplikasi Dimulai")

            val rvKatalog = findViewById<RecyclerView>(R.id.rvKatalog)
            val etSearch = findViewById<EditText>(R.id.etSearch)
            val btnSearch = findViewById<ImageButton>(R.id.btnSearch)
            val btnSort = findViewById<Button>(R.id.btnSort)

            listTampil.addAll(daftarLaptop)

            val orientation = resources.configuration.orientation
            if (orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
                rvKatalog.layoutManager = GridLayoutManager(this, 2)
            }
            else {
                rvKatalog.layoutManager = GridLayoutManager(this, 2)
            }
            val spanCount = if (orientation == 2) 3 else 2
            rvKatalog.layoutManager = GridLayoutManager(this, spanCount)
            val columns = if (resources.configuration.orientation == 2) 2 else 2
            rvKatalog.layoutManager = GridLayoutManager(this, columns)

            adapter = LaptopAdapter(listTampil)
            rvKatalog.adapter = adapter

            btnSearch.setOnClickListener {
                val q = etSearch.text.toString().trim()
                if (q.isEmpty()) {
                    listTampil.clear()
                    listTampil.addAll(daftarLaptop)
                } else {
                    linearSearch(q)
                }
                adapter.notifyDataSetChanged()
            }

            btnSort.setOnClickListener { 
                bubbleSort()
                adapter.notifyDataSetChanged() 
            }
        } catch (e: Exception) { 
            Log.e(nimTag, "Error: ${e.message}") 
        }
    }

    private fun linearSearch(query: String) {
        val hasil = mutableListOf<Laptop>()
        for (l in daftarLaptop) {
            if (l.nama.contains(query, true)) hasil.add(l)
        }
        listTampil.clear()
        listTampil.addAll(hasil)
        Log.d(nimTag, "Search dijalankan for: $query")
    }

    private fun bubbleSort() {
        for (i in 0 until listTampil.size - 1) {
            for (j in 0 until listTampil.size - i - 1) {
                if (listTampil[j].nama.compareTo(listTampil[j+1].nama, true) > 0) {
                    val temp = listTampil[j]
                    listTampil[j] = listTampil[j+1]
                    listTampil[j+1] = temp
                }
            }
        }
        Log.d(nimTag, "Bubble Sort A-Z selesai")
    }

    private inner class LaptopAdapter(private val items: List<Laptop>) : RecyclerView.Adapter<LaptopAdapter.VH>() {
        
        inner class VH(v: View) : RecyclerView.ViewHolder(v) {
            val txtNama: TextView = v.findViewById(R.id.txtNama)
            val imgLaptop: ImageView = v.findViewById(R.id.imgLaptop)
            val txtHarga: TextView = v.findViewById(R.id.txtHarga)
        }

        override fun onCreateViewHolder(p: ViewGroup, t: Int): VH {
            val v = LayoutInflater.from(p.context).inflate(R.layout.item_laptop, p, false)
            return VH(v)
        }

        override fun onBindViewHolder(h: VH, p: Int) {
            val item = items[p]
            h.txtNama.text = item.nama
            h.imgLaptop.setImageResource(item.foto)

            val localeID = Locale.Builder().setLanguage("in").setRegion("ID").build()
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            h.txtHarga.text = formatRupiah.format(item.harga)

            h.itemView.setOnClickListener {
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("NAMA", item.nama)
                    putExtra("HARGA", item.harga)
                    putExtra("DESC", item.desc)
                    putExtra("PROCESSOR", item.processor)
                    putExtra("RAM", item.ram)
                    putExtra("STORAGE", item.storage)
                    putExtra("GRAPHICS", item.graphics)
                    putExtra("DISPLAY", item.display)
                    putExtra("FOTO", item.foto)
                }
                this@MainActivity.startActivity(intent)
            }
        }

        override fun getItemCount(): Int = items.size
    }
}
