package com.testapp.kotlin_extentions

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

//Consists Kotlin Extensions for Replace Fragments , Show Toasts and Json converters

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int
) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        addToBackStack(fragment.javaClass.name)
    }
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int) {
    activity?.supportFragmentManager?.inTransaction {
        replace(frameId, fragment)
        addToBackStack(fragment.javaClass.name)
    }
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    args: Bundle
) {
    supportFragmentManager.inTransaction {
        fragment.arguments = args
        replace(frameId, fragment)
        addToBackStack(fragment.javaClass.name)
    }
}

fun Fragment.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    args: Bundle
) {
    activity?.supportFragmentManager?.inTransaction {
        fragment.arguments = args
        replace(frameId, fragment)
        addToBackStack(fragment.javaClass.name)
    }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}


fun Fragment.showToast(s: String) {
    Toast.makeText(activity?.applicationContext, s, Toast.LENGTH_SHORT).show()
}
fun AppCompatActivity.showToast(s: String) {
    Toast.makeText(this.applicationContext, s, Toast.LENGTH_SHORT).show()
}


fun Fragment.popBack() {
    activity?.supportFragmentManager?.popBackStack()
}


val String.jsonObject: JSONObject?
    get() = try {
        JSONObject(this)
    } catch (e: JSONException) {
        null
    }

val String.jsonArray: JSONArray?
    get() = try {
        JSONArray(this)
    } catch (e: JSONException) {
        null
    }