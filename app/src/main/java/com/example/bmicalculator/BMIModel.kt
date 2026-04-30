package com.example.bmicalculator

enum class BMICategory(val message: String, val colorResId: Int) {
    UNDERWEIGHT("You are underweight", android.R.color.holo_red_dark),
    NORMAL("Your weight is normal", android.R.color.holo_green_dark),
    OVERWEIGHT("You are overweight", android.R.color.holo_red_dark)
}

class Person(val age: Int, val gender: String, val weight: Double, val height: Double) {
    fun calculateBMI(): Double {
        // height is in cm, weight in kg
        val heightInMeters = height / 100
        return weight / (heightInMeters * heightInMeters)
    }

    fun getCategory(bmi: Double): BMICategory {
        return when {
            bmi < 18.5 -> BMICategory.UNDERWEIGHT
            bmi < 25.0 -> BMICategory.NORMAL
            else -> BMICategory.OVERWEIGHT
        }
    }
}