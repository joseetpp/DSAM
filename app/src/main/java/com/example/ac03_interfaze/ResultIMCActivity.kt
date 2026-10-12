package com.example.ac03_interfaze

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ac03_interfaze.MainActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListener()
    }

    private fun initListener() {
        btnRecalculate.setOnClickListener { finish() }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when(result){
            in 0.00..18.50 -> {
                tvResult.text = getString(R.string.title_underweight)
                tvDescription.text= getString(R.string.description_underweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
            }
            in 18.51..24.99 -> {
                tvResult.text= getString(R.string.title_nommal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                tvDescription.text= getString(R.string.description_normal)

            }
            in 25.00..29.99 -> {
                tvResult.text = getString(R.string.title_overweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                tvDescription.text = getString(R.string.description_overweight)
            }
            in 30.00..99.99 -> {
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_obesidad))
                tvResult.text = getString(R.string.title_obesity)
                tvDescription.text = getString(R.string.description_obesity)
            }
            else -> {
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_obesidad))
                tvResult.text = getString(R.string.error_)
                tvDescription.text = getString(R.string.error_)
            }
        }
    }
    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC=findViewById(R.id.tvIMC)
        tvDescription=findViewById(R.id.tvDescription)
        btnRecalculate=findViewById(R.id.btnReCalculate)
    }
}