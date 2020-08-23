package com.testapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.testapp.activities.MainActivity
import com.testapp.R
import com.testapp.databinding.FragmentHomeBinding
import com.testapp.kotlin_extentions.replaceFragment

class HomeFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (baseActivity as MainActivity).setToolbar(getString(R.string.home),true)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.clickHandle = this //Registering custom click interface

    }

    override fun clickHandle(v: View) {
        when (v.id) {
            R.id.serviceBT -> {
                replaceFragment(ServiceFragment(), R.id.frameContainer)
            }
            R.id.threadBT -> {
                replaceFragment(ThreadsFragment(), R.id.frameContainer)
            }
            R.id.asyncTaskBT -> {
                replaceFragment(AsyncTaskFragment(), R.id.frameContainer)
            }
        }
    }
}