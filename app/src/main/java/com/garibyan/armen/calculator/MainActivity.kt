package com.garibyan.armen.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.garibyan.armen.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var isLastNumber: Boolean = false
    var isLastPoint: Boolean = false

    private lateinit var resultText: TextView
    private var operation: String = ""
    private var operand: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultText = binding.resultText
    }

    fun onNumberClick(view: View) {
        if (view is Button) {
            var result = resultText.text.toString()
            val number = view.text.toString()
            if (result == "0") result = ""
            val x = result + number
            resultText.text = x
            isLastNumber = true
            isLastPoint = false
        }
    }

    fun onOperationClick(view: View) {
        if (view is Button) {
            if (isLastNumber) {
                operation = view.text.toString()
                operand = resultText.text.toString().toDouble()
                resultText.text = ""
                isLastPoint = false
                isLastNumber = false
            }
        }
    }

    fun onEqualsClick(view: View) {
        val isEmptyTv = resultText.text.isEmpty()
        if (!isLastPoint && !isEmptyTv) {
            val secondNumber: Double = resultText.text.toString().toDouble()
            val k = when (operation) {
                "+" -> operand + secondNumber
                "-" -> operand - secondNumber
                "/" -> operand / secondNumber
                "*" -> operand * secondNumber
                else -> 0.0
            }
            resultText.text = k.toString()
            operand = 0.0
        }
    }

    fun onCleanAllClick(view: View) {
        resultText.text = ""
        isLastPoint = false
        isLastNumber = false
    }

    fun onPointClick(view: View) {
        var containsPoint = resultText.text.toString().contains(".")
        if (view is Button) {
            if (!isLastPoint && !containsPoint) {
                val result = resultText.text.toString()
                val point = view.text.toString()
                val x = result + point
                resultText.text = x
                isLastPoint = true
            }
        }
    }
}