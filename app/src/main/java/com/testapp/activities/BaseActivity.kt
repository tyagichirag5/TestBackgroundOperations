package com.testapp.activities

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.testapp.R
import com.testapp.kotlin_extentions.showToast


open class BaseActivity:AppCompatActivity() {//Common Class for Activities
    private var exit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    private fun exitFromApp() {
        finish()
        finishAffinity()
    }


    fun exitApp() {
        if (exit) {
            exitFromApp() // finish activity
        } else {
            showToast(getString(R.string.press_to_exit))
            exit = true
            Handler(Looper.getMainLooper()).postDelayed(Runnable { exit = false }, 3 * 1000)
        }
    }

      fun isMyServiceRunning(serviceClass: Class<*>): Boolean {//Method to check whether a service is running or not
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }


}