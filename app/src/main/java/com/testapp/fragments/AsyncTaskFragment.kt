package com.testapp.fragments

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.testapp.R
import com.testapp.activities.MainActivity
import com.testapp.adapters.AsyncTaskAdapter
import com.testapp.databinding.FragmentAsyncBinding
import com.testapp.kotlin_extentions.jsonArray
import com.testapp.model_classes.CountriesData
import com.testapp.utils.Const
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class AsyncTaskFragment : BaseFragment() {
    lateinit var binding: FragmentAsyncBinding
    var arrayList: ArrayList<CountriesData>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (baseActivity as MainActivity).setToolbar(getString(R.string.async_task), true)
        binding = FragmentAsyncBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()//Initialize operations
    }

    private fun initUI() {
        arrayList?.clear()

        binding.listRV.addItemDecoration(
            DividerItemDecoration(
                baseActivity,
                LinearLayoutManager.VERTICAL
            )
        )
        getApiInfo()
    }

    private fun getApiInfo() {
        JSONAsyncTask().execute(Const.URL)//Calling API with AsyncTask

    }

    @SuppressLint("StaticFieldLeak")
    inner class JSONAsyncTask : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            binding.progressPB.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: String?): String? {
            try {
                val url = URL(params[0])
                val urlConnection: HttpsURLConnection = url.openConnection() as HttpsURLConnection


                // Check the connection status.
                val statusCode = urlConnection.responseCode
                // Connection success. Proceed to fetch the response
                if (statusCode == Const.HTTP_OK) {
                    val stream: InputStream = BufferedInputStream(urlConnection.inputStream)
                    val bufferedReader = BufferedReader(InputStreamReader(stream))
                    val builder = StringBuilder()
                    var inputString: String?
                    while (bufferedReader.readLine().also { inputString = it } != null) {
                        builder.append(inputString)
                    }
                    return builder.toString()
                } else {
                    Log.e("STATUS", "NOK")
                }

            } catch (ex: Exception) {
                ex.stackTrace
            }

            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            binding.progressPB.visibility = View.GONE
            if (!result.isNullOrEmpty()) {
                val json = result.toString().jsonArray
                if (json?.length()!! > 0) {//Checking Length of Array
                    arrayList = ArrayList()
                    for (i in 0 until json.length()) {
                        val data: CountriesData = Gson().fromJson(
                            json.getJSONObject(i).toString(),
                            CountriesData::class.java
                        )
                        arrayList!!.add(data)
                    }
                    setAdapter()//Calling adapter
                }

            }

        }


    }

    private fun setAdapter() {
        val adapter = AsyncTaskAdapter(baseActivity!!, arrayList!!)
        binding.listRV.adapter = adapter
    }

}