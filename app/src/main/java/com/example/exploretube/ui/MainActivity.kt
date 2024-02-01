package com.example.exploretube.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exploretube.R
import com.example.exploretube.adapter.HorizontalAdapter
import com.example.exploretube.adapter.VerticalAdapter
import com.example.exploretube.databinding.ActivityMainBinding
import com.example.exploretube.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var _binding: ActivityMainBinding
    private lateinit var verticalAdapter: VerticalAdapter
    private lateinit var horizontalAdapter: HorizontalAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(_binding.root)
        setUpView()
        steUpObservers()

    }

    private fun steUpObservers() {

        viewModel.getPhotosFromDB().observe(this) {
            horizontalAdapter.submitList(it)
        }

        viewModel.getVideosFromDB().observe(this) {
            verticalAdapter.submitList(it)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        viewModel.error.observe(this) { message ->
            message?.let {
                Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun setUpView() {

        verticalAdapter = VerticalAdapter()
        horizontalAdapter = HorizontalAdapter()

        _binding.rvHomeScreen.apply {
            layoutManager = LinearLayoutManager(_binding.root.context)
            setHasFixedSize(true)
            this.adapter = verticalAdapter
        }

        _binding.rvHorizontal.apply {
            layoutManager =
                LinearLayoutManager(_binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = horizontalAdapter
        }
    }

    private fun showLoading() {
        _binding.apply {
            parentLayout.visibility = View.GONE
            spinnerLoading.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        _binding.apply {
            spinnerLoading.visibility = View.GONE
            parentLayout.visibility = View.VISIBLE
        }
    }
}