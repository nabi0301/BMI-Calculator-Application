package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bmi = intent.getDoubleExtra("BMI_VALUE", 0.0)
        val categoryName = intent.getStringExtra("BMI_CATEGORY") ?: BMICategory.NORMAL.name
        val category = BMICategory.valueOf(categoryName)

        val tvBMIValue = findViewById<TextView>(R.id.tvBMIValue)
        val tvBMIMessage = findViewById<TextView>(R.id.tvBMIMessage)
        val btnReCalculate = findViewById<Button>(R.id.btnReCalculate)

        tvBMIValue.text = "%.2f".format(bmi)
        tvBMIMessage.text = category.message
        tvBMIMessage.setTextColor(ContextCompat.getColor(this, category.colorResId))

        btnReCalculate.setOnClickListener {
            finish()
        }
    }
}