package com.example.ac03_interfaze

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    private lateinit var cardHome: MaterialCardView
    private lateinit var cardDona: MaterialCardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
            initComponents()
            initListeners()
        }
    }
    private fun initComponents(){
        //Inicializamos
        cardHome=findViewById(R.id.seleccio_home)
        cardDona=findViewById(R.id.seleccio_dona)
    }
    private fun initListeners(){
        //Asigno eventos
        cardHome.setOnClickListener {  }
        cardDona.setOnClickListener {  }
    }
    private fun

}