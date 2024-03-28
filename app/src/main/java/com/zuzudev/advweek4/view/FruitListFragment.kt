package com.zuzudev.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zuzudev.advweek4.R
import com.zuzudev.advweek4.databinding.FragmentFruitListBinding
import com.zuzudev.advweek4.databinding.FragmentStudentListBinding
import com.zuzudev.advweek4.viewmodel.FruitListViewModel
import com.zuzudev.advweek4.viewmodel.ListViewModel


class FruitListFragment : Fragment() {
    private lateinit var viewModel: FruitListViewModel
    private val fruitListAdapter = FruitListAdapter(arrayListOf())
    private lateinit var binding: FragmentFruitListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFruitListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FruitListViewModel::class.java)
        viewModel.refresh()
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = fruitListAdapter
        observeViewModel()
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            binding.refreshLayout.isRefreshing = false
        }
    }
    fun observeViewModel() {
        viewModel.fruitLD.observe(viewLifecycleOwner, Observer {
            fruitListAdapter.updateFruitList(it)
        })


        viewModel.fruitLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })


        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        })

    }
}