package com.example.exploretube.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
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
    private lateinit var verticalAdapter:VerticalAdapter
    private lateinit var horizontalAdapter: HorizontalAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setContentView(_binding.root)
        setUpView()
        steUpObservers()
        setUpSpinner()
    }

    private fun setUpSpinner() {

    }

    private fun steUpObservers() {
        lifecycleScope.launch {
            viewModel.videos.collectLatest {
                verticalAdapter.submitList(it)
            }
        }
        lifecycleScope.launch {
            viewModel.photos.collectLatest {
                horizontalAdapter.submitList(it)
            }
        }

        viewModel.isLoading.observe(this) {isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
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
            layoutManager = LinearLayoutManager(_binding.root.context, LinearLayoutManager.HORIZONTAL, false)
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