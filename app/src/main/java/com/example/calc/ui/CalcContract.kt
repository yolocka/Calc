package com.example.calc.ui

class CalcContract {

    companion object {
        const val BASEURL = "https://api.apilayer.com/"
        const val ERROR_MASSAGE = "ERROR"
    }

    interface CalcView {
        fun <T> addFigures (figure: T)
        fun showCurrency(currency: String)
    }

    interface Presenter {
        fun plus(a: Double): Double
        fun minus(a: Double): Double
        fun division(a: Double): Double
        fun multiplication(a: Double): Double
        fun percent(a: Double): Double
        fun equal(a: Double): Double
        fun clearResult()
        fun getCurrency()
    }
}