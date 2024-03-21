package com.zuzudev.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zuzudev.advweek4.R
import com.zuzudev.advweek4.databinding.FragmentStudentDetailBinding
import com.zuzudev.advweek4.viewmodel.DetailViewModel
import com.zuzudev.advweek4.viewmodel.ListViewModel


class StudentDetailFragment : Fragment() {
    private lateinit var binding:FragmentStudentDetailBinding
    private lateinit var viewModel:DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.txtId.setText(it.id)
            binding.txtName.setText(it.name)
            binding.txtDob.setText(it.dob)
            binding.txtPhone.setText(it.phone)
        })

    }

}