package com.example.calc

import com.example.calc.repo.RemoteRepository
import com.example.calc.ui.CalcContract
import com.example.calc.ui.CalcPresenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class CalcPresenterTest {

    private lateinit var presenter: CalcPresenter

    @Mock
    private lateinit var repository: RemoteRepository

    @Mock
    private lateinit var viewContract: CalcContract.CalcView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CalcPresenter(repository, viewContract)
    }

    @Test
    fun getCurrency_Test() {
        val searchQuery = "USD"
        presenter.getCurrency()
        Mockito.verify(repository, Mockito.times(1)).getCurrency(searchQuery, presenter)
    }

    @Test
    fun handleResponse_ResponseUnsuccessful() {
        val response = Mockito.mock(Response::class.java) as Response<CurrencyResponse?>
        Mockito.`when`(response.isSuccessful).thenReturn(false)
        Assert.assertFalse(response.isSuccessful)
    }

    @Test
    fun handleGitHubResponse_ResponseIsEmpty() {
        val response = Mockito.mock(Response::class.java) as Response<CurrencyResponse?>
        Mockito.`when`(response.body()).thenReturn(null)
        presenter.handleResponse(response)
        Assert.assertNull(response.body())
    }

    @Test
    fun handleGitHubResponse_ResponseIsNotEmpty() {
        val response = Mockito.mock(Response::class.java) as Response<CurrencyResponse?>
        Mockito.`when`(response.body()).thenReturn(Mockito.mock(CurrencyResponse::class.java))
        presenter.handleResponse(response)
        Assert.assertNotNull(response.body())
    }

    @Test
    fun handleGitHubResponse_Success() {
        val response = Mockito.mock(Response::class.java) as Response<CurrencyResponse?>
        val currencyResponse = Mockito.mock(CurrencyResponse::class.java)
        val quotes = Mockito.mock(Quotes::class.java)

        Mockito.`when`(response.isSuccessful).thenReturn(true)
        Mockito.`when`(response.body()).thenReturn(currencyResponse)
        Mockito.`when`(currencyResponse.quotes).thenReturn(quotes)
        Mockito.`when`(quotes.usdRub).thenReturn(56.1)

        presenter.handleResponse(response)

        Mockito.verify(viewContract, Mockito.times(1)).showCurrency("56.1")
    }

}