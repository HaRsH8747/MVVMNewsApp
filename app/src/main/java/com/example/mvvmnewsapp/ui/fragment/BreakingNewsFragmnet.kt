package com.example.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.ui.NewsActivity
import com.example.mvvmnewsapp.ui.NewsViewModel
import com.example.mvvmnewsapp.ui.adapter.NewsAdapter
import com.example.mvvmnewsapp.ui.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragmnet: Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            Log.d("CLEAR","breakingNews: ${viewModel.breakingNews}")
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An Error Occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        Log.d("CLEAR","Inside setUp")
        rvBreakingNews.apply {
            adapter = newsAdapter
            Log.d("CLEAR","adapter: ${adapter.hashCode()}")
            layoutManager = LinearLayoutManager(activity)
        }
    }
}