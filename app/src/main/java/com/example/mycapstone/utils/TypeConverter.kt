package com.example.mycapstone.utils

object TypeConverter {
    fun doubleToString(num: Double?): String {
        return if (num != null) String.format("%.2f", num) else "N/A"
    }

}