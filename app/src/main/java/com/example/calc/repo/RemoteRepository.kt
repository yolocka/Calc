package com.example.calc.repo

import com.example.calc.CurrencyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository(private val currencyApi: CurrencyApi) {

    fun getCurrency(
        currency: String,
        callback: RepositoryCallback
    ) {
        val call = currencyApi.getCurrency(currency)
        call?.enqueue(object : Callback<CurrencyResponse?> {

            override fun onResponse(
                call: Call<CurrencyResponse?>,
                response: Response<CurrencyResponse?>
            ) {
                callback.handleResponse(response)
            }

            override fun onFailure(
                call: Call<CurrencyResponse?>,
                t: Throwable
            ) {
                callback.handleError()
            }
        })
    }

    interface RepositoryCallback {
        fun handleResponse(response: Response<CurrencyResponse?>?)
        fun handleError()
    }
}