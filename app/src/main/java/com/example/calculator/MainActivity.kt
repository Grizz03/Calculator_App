package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
//  Main Calc function
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    //    Simple clear function on button press
    fun onClear(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    //    Unable to use multiple decimals
    fun onDec(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if (!(tvInput.text.contains("."))) {
            tvInput.append((view as Button).text)
        }
    }

    fun onOperator(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+")
        }
    }

    fun onEqual(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if (lastNumeric) {
            // Convert to string
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    // 99-1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = (one.toDouble() - two.toDouble()).toString()
                } else if (tvValue.contains("+")) {
                        val splitValue = tvValue.split("+")
                        // 99-1
                        var one = splitValue[0] // 99
                        var two = splitValue[1] // 1
                        if(!prefix.isEmpty()){
                            one = prefix + one
                        }
                        tvInput.text = (one.toDouble() + two.toDouble()).toString()
                    } else  if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    // 99-1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = (one.toDouble() * two.toDouble()).toString()
                } else  if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    // 99-1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = (one.toDouble() / two.toDouble()).toString()
                }

            } catch (e: ArithmeticException) { // So app wont crash if something goes wrong.
                e.printStackTrace()
            }
        }
    }

}

