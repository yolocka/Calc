package com.example.calc

class CalcContract {

    interface CalcView {
        fun <T> addFigures (figure: T)
    }

    interface Presenter {
        fun plus(a: Double): Double
        fun minus(a: Double): Double
        fun division(a: Double): Double
        fun multiplication(a: Double): Double
        fun percent(a: Double): Double
        fun equal(a: Double): Double
        fun clearResult()
    }
}