package com.example.ac03_interfaze

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private var isMaleSelected:Boolean = true
    private var isFemaleSelectec: Boolean = false
    private var currentHeight:Int = 120
    private var currentWeight:Int = 70
    private var currentAge:Int = 18
    private lateinit var cardHome: MaterialCardView
    private lateinit var cardDona: MaterialCardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }

    //BTN
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnCalculate: Button
    private lateinit var tvAge: TextView
    private lateinit var tvWeight: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        initUI()
    }


    private fun initComponents(){
        //Inicializamos
        cardHome=findViewById(R.id.seleccio_home)
        cardDona=findViewById(R.id.seleccio_dona)
        tvHeight=findViewById(R.id.tvHeight)
        rsHeight=findViewById(R.id.rsHeight)
        btnAddAge=findViewById(R.id.btnAddAge)
        btnSubtractAge=findViewById(R.id.btnSubtractAge)
        btnAddWeight=findViewById(R.id.btnAddWeight)
        btnSubtractWeight=findViewById(R.id.btnSubtractWeight)
        btnCalculate=findViewById(R.id.btnCalculate)
        tvAge=findViewById(R.id.tvAge)
        tvWeight=findViewById(R.id.tvWeight)
    }
    private fun initListeners(){
        //Asigno eventos
        cardHome.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        cardDona.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, values,_  ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(values).toInt()
            tvHeight.text="$currentHeight"

        }
        btnAddWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }

        btnAddAge.setOnClickListener {
            currentAge +=1
            setAge()
        }
        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result:Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)

    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight/(currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeight(){
        tvWeight.text= currentWeight.toString()
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelectec = !isFemaleSelectec
    }
    private fun setGenderColor(){
        cardDona.setBackgroundColor(getBackgroundColor(isFemaleSelectec))
        cardHome.setBackgroundColor(getBackgroundColor(isMaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean):Int{
        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
        } else{
            R.color.card_background_purple
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}