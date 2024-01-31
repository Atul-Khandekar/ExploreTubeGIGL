package com.example.exploretube.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exploretube.R
import com.example.exploretube.adapter.ParentAdapter
import com.example.exploretube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val parentAdapter = ParentAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()

    }

    private fun setUpView() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.rvHomeScreen.apply {
            layoutManager = LinearLayoutManager(_binding.root.context)
            setHasFixedSize(true)
            adapter = parentAdapter
        }
    }

}