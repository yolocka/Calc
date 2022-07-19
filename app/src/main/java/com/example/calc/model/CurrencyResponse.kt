package com.example.calc

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyResponse (
    @SerializedName("quotes")
    @Expose
    val quotes: Quotes?  = Quotes()
        )

data class Quotes (

    @SerializedName("USDRUB" ) var usdRub : Double? = null,

)
