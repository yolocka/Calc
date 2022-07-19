package com.example.calc.ui

import com.example.calc.CurrencyResponse
import com.example.calc.repo.RemoteRepository
import com.example.calc.ui.CalcContract.Companion.ERROR_MASSAGE
import retrofit2.Response

class CalcPresenter (
    private val repository: RemoteRepository,
    private val viewContract: CalcContract.CalcView
    ) : CalcContract.Presenter, RemoteRepository.RepositoryCallback {

    var isActionDone: Boolean = false
    var isPlusPressed: Boolean = false
    var isMinusPressed: Boolean = false
    var isDivisionPressed: Boolean = false
    var isMultiplicationPressed: Boolean = false
    private val currency = "USD"
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

    override fun getCurrency() {
        repository.getCurrency(currency, this)
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

    override fun handleResponse(response: Response<CurrencyResponse?>?) {
        if (response != null && response.isSuccessful) {
            val currencyResponse = response.body()
            val currencyValue = currencyResponse?.quotes?.usdRub
            viewContract.showCurrency(currencyValue.toString())
        }
    }

    override fun handleError() {
        viewContract.showCurrency(ERROR_MASSAGE)
    }
}