package com.testapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.testapp.R
import com.testapp.activities.MainActivity
import com.testapp.databinding.FragmentServiceBinding
import com.testapp.utils.MyService


class ServiceFragment : BaseFragment() {
    lateinit var binding: FragmentServiceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (baseActivity as MainActivity).setToolbar(getString(R.string.service), true)
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.clickHandle = this
        if (baseActivity!!.isMyServiceRunning(MyService::class.java)) {
            binding.statusTV.text = getString(R.string.service_running)
        } else {
            binding.statusTV.text = getString(R.string.service_stopped)
        }
    }

    override fun clickHandle(v: View) {
        when (v.id) {
            R.id.startBT -> {
                startService()//Method to start the service
            }

            R.id.stopBT -> {
                stopService()//Method to stop the service
            }
        }
    }

    fun startService() {
        val i = Intent(baseActivity, MyService::class.java)
        baseActivity!!.startService(i)
        initUI()
    }

    fun stopService() {
        baseActivity!!.stopService(Intent(baseActivity, MyService::class.java))
        initUI()
    }


}