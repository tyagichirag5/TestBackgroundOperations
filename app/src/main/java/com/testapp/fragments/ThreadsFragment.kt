package com.testapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.testapp.R
import com.testapp.activities.MainActivity
import com.testapp.databinding.FragmentThreadBinding
import com.testapp.kotlin_extentions.showToast


class ThreadsFragment : BaseFragment() {
    private lateinit var runnable: ExampleRunnable
    lateinit var binding: FragmentThreadBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (baseActivity as MainActivity).setToolbar(getString(R.string.thread), true)
        binding = FragmentThreadBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.clickHandle = this
    }

    override fun clickHandle(v: View) {
        when (v.id) {
            R.id.startBT -> {
                if (isValidate()) {
                    startThread()//call the method to start the thread
                }
            }
        }
    }

    private fun isValidate(): Boolean {
        when {
            binding.timeET.text.toString()
                .isEmpty() -> showToast(getString(R.string.enter_time_interval))
            else -> return true
        }
        return false
    }

    private fun startThread() {
        val time = binding.timeET.text.toString().toInt()
        runnable = ExampleRunnable(time)
        Thread(runnable).start()//Start the thread
        binding.timeET.text?.clear()
    }


    inner class ExampleRunnable(var seconds: Int) : Runnable {

        override fun run() {
            for (i in 0 until seconds) {
                Log.e("THREAD", "startThread: $i")
                try {
                    Thread.sleep(1000)// suspend execution for a specified period
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                    Log.e("Exception", e.message.toString())
                }
            }
        }
    }

}