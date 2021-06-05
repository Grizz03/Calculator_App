package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

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

}

