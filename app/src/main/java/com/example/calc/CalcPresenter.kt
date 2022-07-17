package com.example.calc

class CalcPresenter : CalcContract.Presenter {

    var isActionDone: Boolean = false
    var isPlusPressed: Boolean = false
    var isMinusPressed: Boolean = false
    var isDivisionPressed: Boolean = false
    var isMultiplicationPressed: Boolean = false
    private var result : Double = 0.0

    override fun plus(a: Double): Double {
        action(a)
        isPlusPressed = true
        return result
    }

    override fun minus(a: Double): Double {
        action(a)
        isMinusPressed = true
        return result
    }

    override fun division(a: Double): Double {
        action(a)
        isDivisionPressed = true
        return result
    }

    override fun multiplication(a: Double): Double {
        action(a)
        isMultiplicationPressed = true
        return result
    }

    override fun percent(a: Double): Double {
        action(a)
        return result/100
    }

    override fun equal(a: Double) : Double {
        action(a)
        val totalResult = result
        result = 0.0
        return totalResult
    }

    override fun clearResult() {
        result = 0.0
    }

    private fun reset() {
        isPlusPressed = false
        isMinusPressed = false
        isDivisionPressed = false
        isMultiplicationPressed = false
    }

    private fun action(a: Double) {
        if (isPlusPressed) result += a
        if (isMinusPressed) result -= a
        if (isDivisionPressed) result /= a
        if (isMultiplicationPressed) result *= a
        if (result == 0.0) result = a
        reset()
        isActionDone = true
    }
}