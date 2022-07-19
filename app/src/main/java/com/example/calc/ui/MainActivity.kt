package com.example.calc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calc.repo.CurrencyApi
import com.example.calc.repo.RemoteRepository
import com.example.calc.ui.CalcContract.Companion.BASEURL
import com.example.calc.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CalcContract.CalcView {

    private lateinit var binding: ActivityMainBinding
    private var presenter = CalcPresenter(createRepository(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickListenersSetUp()

    }

    override fun <T> addFigures (figure: T) {
        if (!presenter.isActionDone) {
            val result = binding.figuresEditText.text
            binding.figuresEditText.setText("$result$figure")
        } else {
            presenter.isActionDone = false
            binding.figuresEditText.setText("$figure")
        }
    }

    override fun showCurrency(currency: String) {
        binding.figuresEditText.setText(currency)
        presenter.isActionDone = true
    }

    private fun clickListenersSetUp() {
        with(binding) {
            oneButton.setOnClickListener {
                addFigures(1)
            }
            twoButton.setOnClickListener {
                addFigures(2)
            }
            threeButton.setOnClickListener {
                addFigures(3)
            }
            fourButton.setOnClickListener {
                addFigures(4)
            }
            fiveButton.setOnClickListener {
                addFigures(5)
            }
            sixButton.setOnClickListener {
                addFigures(6)
            }
            sevenButton.setOnClickListener {
                addFigures(7)
            }
            eightButton.setOnClickListener {
                addFigures(8)
            }
            nineButton.setOnClickListener {
                addFigures(9)
            }
            zeroButton.setOnClickListener {
                if (!binding.figuresEditText.text.isNullOrEmpty()) addFigures(0)
            }
            separatorButton.setOnClickListener {
                var isSeparatorExist = false
                binding.figuresEditText.text.toString().forEach {
                    if (it == '.') isSeparatorExist = true
                }
                if (!isSeparatorExist) {
                    addFigures(".")
                }
            }
            cleanButton.setOnClickListener {
                binding.figuresEditText.text?.clear()
                presenter.clearResult()
            }
            negativeButton.setOnClickListener {
                val length = binding.figuresEditText.text?.length
                binding.figuresEditText.apply {
                    if (text?.substring(0, 1) == "-") {
                        setText(length.let {
                            text?.substring(1,
                                it?:0
                            )
                        })
                    } else {
                        setText("-$text")
                    }
                }
            }
            plusButton.setOnClickListener {
                val figure = binding.figuresEditText.text.toString().toDouble()
                addFigures(presenter.plus(figure))
                presenter.isActionDone = true
            }
            minusButton.setOnClickListener {
                val figure = binding.figuresEditText.text.toString().toDouble()
                addFigures(presenter.minus(figure))
                presenter.isActionDone = true
            }
            divisionButton.setOnClickListener {
                val figure = binding.figuresEditText.text.toString().toDouble()
                addFigures(presenter.division(figure))
                presenter.isActionDone = true
            }
            multiplicationButton.setOnClickListener {
                val figure = binding.figuresEditText.text.toString().toDouble()
                addFigures(presenter.multiplication(figure))
                presenter.isActionDone = true
            }
            equalButton.setOnClickListener {
                val figure = binding.figuresEditText.text.toString().toDouble()
                addFigures(presenter.equal(figure))
                presenter.isActionDone = true
            }
            percentButton.setOnClickListener {
                val figure = binding.figuresEditText.text.toString().toDouble()
                addFigures(presenter.percent(figure))
                presenter.isActionDone = true
            }
            currencyButton.setOnClickListener {
                presenter.getCurrency()
            }
        }
    }
    private fun createRepository(): RemoteRepository {
        return RemoteRepository(createRetrofit().create(CurrencyApi::class.java))
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}