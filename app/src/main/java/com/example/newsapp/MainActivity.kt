package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.repository.db.ArticleDataBase
import com.example.newsapp.viewModel.NewsViewModel
import com.example.newsapp.viewModel.NewsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDataBase(this))
        val viewModelProvider = NewsViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProvider).get(NewsViewModel::class.java)

        bottomNavigationView.setupWithNavController(newsFragment.findNavController())
    }
}