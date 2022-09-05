package com.example.factorialcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.factorialcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        binding.buttonCalculate.setOnClickListener{
            viewModel.calculate(binding.editTextNumber.text.toString())
        }

    }

    private fun observeViewModel(){
        viewModel.state.observe(this){
            if(it.isError){
                Toast.makeText(this, "Необходимо ввести значение", Toast.LENGTH_SHORT).show()
            }
            if(it.isInProgress){
                binding.progressBarLoading.visibility = View.VISIBLE
                binding.buttonCalculate.isEnabled = false
            }
            else {
                binding.progressBarLoading.visibility = View.GONE
                binding.buttonCalculate.isEnabled = true
            }
            binding.textView.text = it.factorial
        }
    }
}