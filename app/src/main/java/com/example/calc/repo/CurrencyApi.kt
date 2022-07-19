package com.example.calc.repo

import com.example.calc.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyApi {
        @Headers("apikey: 1iCpg1K9Txbg0z8k1Q20ncs9AI8hjLRu")
        @GET("currency_data/live")
        fun getCurrency(@Query("base")  base: String?): Call<CurrencyResponse?>?
}