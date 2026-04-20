package com.example.uas_pemrogseluler_42430007_gabriel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString().trim()

            if (query.isEmpty()) {
                Toast.makeText(this, "Silakan masukkan nama laptop!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, DetailActivity::class.java)

                intent.putExtra("EXTRA_NAMA", "Lenovo Legion 5 (Simulasi)")
                intent.putExtra("EXTRA_HARGA", 18500000)
                intent.putExtra("EXTRA_DESC", "Hasil pencarian untuk: $query. Ini adalah laptop performa tinggi.")
                intent.putExtra("EXTRA_FOTO", R.drawable.lenovo_legion_5) 

                startActivity(intent)
            }
        }
    }
}
