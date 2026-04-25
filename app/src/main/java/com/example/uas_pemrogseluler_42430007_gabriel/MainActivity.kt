package com.example.uas_pemrogseluler_42430007_gabriel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.*

data class Laptop(val nama: String, val harga: Int, val desc: String, val foto: Int)

class MainActivity : AppCompatActivity() {

    private val nimTag = "42430007"
    private var listTampil = mutableListOf<Laptop>()
    private lateinit var lvKatalog: ListView

    private val daftarLaptop = arrayOf(
        Laptop("Lenovo Chromebook Flex", 5500000, "Chromebook fleksibel untuk kerja ringan dan browsing.", R.drawable.lenovo_chromebook_flex),
        Laptop("Lenovo IdeaPad Chromebook", 6000000, "Laptop ChromeOS simpel untuk pelajar and kerja online.", R.drawable.lenovo_ideapad_chromebook),
        Laptop("Lenovo IdeaPad Flex", 9500000, "Laptop 2-in-1 serbaguna dengan layar sentuh.", R.drawable.lenovo_ideapad_flex),
        Laptop("Lenovo IdeaPad Gaming", 12500000, "Laptop gaming entry-level dengan performa stabil.", R.drawable.lenovo_ideapad_gaming),
        Laptop("Lenovo IdeaPad Slim 1", 4500000, "Laptop basic untuk tugas ringan dan office.", R.drawable.lenovo_ideapad_slim_1),
        Laptop("Lenovo IdeaPad Slim 3", 7500000, "Laptop populer untuk kuliah dan kerja harian.", R.drawable.lenovo_ideapad_slim_3),
        Laptop("Lenovo IdeaPad Slim 5", 10500000, "Laptop tipis dengan performa lebih kencang.", R.drawable.lenovo_ideapad_slim_5),
        Laptop("Lenovo Legion 5", 18500000, "Laptop gaming powerful dengan GPU RTX.", R.drawable.lenovo_legion_5),
        Laptop("Lenovo Legion 5 Pro", 23000000, "Gaming laptop high-end dengan layar premium.", R.drawable.lenovo_legion_5_pro),
        Laptop("Lenovo Legion 7", 28000000, "Flagship gaming Lenovo dengan performa maksimal.", R.drawable.lenovo_legion_7),
        Laptop("Lenovo LOQ 16", 15500000, "Gaming laptop terbaru dengan performa stabil.", R.drawable.lenovo_loq_16),
        Laptop("Lenovo ThinkBook 14", 11000000, "Laptop bisnis ringan dengan desain modern.", R.drawable.lenovo_thinkbook_14),
        Laptop("Lenovo ThinkBook 15", 12000000, "Laptop bisnis layar besar untuk produktivitas.", R.drawable.lenovo_thinkbook_15),
        Laptop("Lenovo ThinkBook 16", 13500000, "Laptop kerja dengan layar luas and nyaman.", R.drawable.lenovo_thinkbook_16),
        Laptop("Lenovo ThinkPad E Series", 14000000, "Laptop bisnis entry ThinkPad dengan build kuat.", R.drawable.lenovo_thinkpad_e_series),
        Laptop("Lenovo ThinkPad L Series", 15500000, "Laptop bisnis dengan durability tinggi.", R.drawable.lenovo_thinkpad_l_series),
        Laptop("Lenovo ThinkPad P Series", 30000000, "Mobile workstation untuk desain and rendering.", R.drawable.lenovo_thinkpad_p_series),
        Laptop("Lenovo ThinkPad T Series", 22000000, "Laptop bisnis premium dengan performa tinggi.", R.drawable.lenovo_thinkpad_t_series),
        Laptop("Lenovo ThinkPad X Series", 25000000, "Laptop ultra ringan kelas premium.", R.drawable.lenovo_thinkpad_x_series),
        Laptop("Lenovo Yoga 7", 16000000, "Laptop 2-in-1 premium untuk produktivitas.", R.drawable.lenovo_yoga_7),
        Laptop("Lenovo Yoga 9", 21000000, "Laptop convertible high-end dengan fitur lengkap.", R.drawable.lenovo_yoga_9),
        Laptop("Lenovo Yoga Duet", 14000000, "Laptop detachable fleksibel ala tablet.", R.drawable.lenovo_yoga_duet),
        Laptop("Lenovo Yoga Slim", 17000000, "Laptop tipis premium dengan performa cepat.", R.drawable.lenovo_yoga_slim),
        Laptop("Lenovo LOQ 15", 14500000, "Laptop gaming mid-range dengan performa solid.", R.drawable.lenovo_loq_15)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        Log.d(nimTag, "Aplikasi Berhasil Dijalankan")

        lvKatalog = findViewById(R.id.lvKatalog)
        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnSort = findViewById<Button>(R.id.btnSort)

        listTampil.addAll(daftarLaptop)
        refreshAdapter()

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString().trim()
            Log.d(nimTag, "User melakukan pencarian: $query")

            if (query.isEmpty()) {
                listTampil.clear()
                listTampil.addAll(daftarLaptop)
                refreshAdapter()
            } else {
                val filterList = daftarLaptop.filter { it.nama.contains(query, ignoreCase = true) }
                if (filterList.isNotEmpty()) {
                    listTampil.clear()
                    listTampil.addAll(filterList)
                    refreshAdapter()
                } else {
                    Toast.makeText(this, "Laptop tidak ditemukan!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnSort.setOnClickListener {
            listTampil.sortBy { it.nama }
            refreshAdapter()
            Log.d(nimTag, "Data diurutkan")
        }
    }

    private class ViewHolder(view: View) {
        val img: ImageView = view.findViewById(R.id.imgLaptop)
        val txtNama: TextView = view.findViewById(R.id.txtNama)
        val txtHarga: TextView = view.findViewById(R.id.txtHarga)
    }

    private fun refreshAdapter() {
        lvKatalog.adapter = object : BaseAdapter() {
            override fun getCount(): Int = listTampil.size
            override fun getItem(position: Int): Any = listTampil[position]
            override fun getItemId(position: Int): Long = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val view: View
                val holder: ViewHolder

                if (convertView == null) {
                    view = layoutInflater.inflate(R.layout.item_laptop, parent, false)
                    holder = ViewHolder(view)
                    view.tag = holder
                } else {
                    view = convertView
                    holder = view.tag as ViewHolder
                }

                val laptop = listTampil[position]

                holder.img.setImageResource(laptop.foto)
                holder.txtNama.text = laptop.nama

                val localeID = Locale.Builder().setLanguage("in").setRegion("ID").build()
                val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
                holder.txtHarga.text = formatRupiah.format(laptop.harga)

                view.setOnClickListener {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra("EXTRA_NAMA", laptop.nama)
                        putExtra("EXTRA_HARGA", laptop.harga)
                        putExtra("EXTRA_DESC", laptop.desc)
                        putExtra("EXTRA_FOTO", laptop.foto)
                    }
                    startActivity(intent)
                }

                return view
            }
        }
    }
}