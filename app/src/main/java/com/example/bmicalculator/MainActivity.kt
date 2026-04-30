package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etAge = findViewById<EditText>(R.id.etAge)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val etHeight = findViewById<EditText>(R.id.etHeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            val ageStr = etAge.text.toString()
            val weightStr = etWeight.text.toString()
            val heightStr = etHeight.text.toString()
            val selectedGenderId = rgGender.checkedRadioButtonId

            if (ageStr.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty() || selectedGenderId == -1) {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageStr.toInt()
            val weight = weightStr.toDouble()
            val height = heightStr.toDouble()
            val gender = findViewById<RadioButton>(selectedGenderId).text.toString()

            // OOP Concept: Creating object of the Person class
            val person = Person(age, gender, weight, height)
            val bmi = person.calculateBMI()
            val category = person.getCategory(bmi)

            // Intent to display the Result UI
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("BMI_VALUE", bmi)
                putExtra("BMI_CATEGORY", category.name)
            }
            startActivity(intent)
        }
    }
}