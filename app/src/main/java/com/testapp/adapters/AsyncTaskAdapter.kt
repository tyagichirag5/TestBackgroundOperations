package com.testapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testapp.activities.BaseActivity
import com.testapp.databinding.AdapterAsyncTaskBinding
import com.testapp.model_classes.CountriesData

class AsyncTaskAdapter(
    val baseActivity: BaseActivity,
    val arrayList: ArrayList<CountriesData>
) :
    RecyclerView.Adapter<AsyncTaskAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: AdapterAsyncTaskBinding =
            AdapterAsyncTaskBinding.inflate(baseActivity.layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val model = getItem(position)//Retrieve values from array list
        holder.onBind(model)//Pass on values to xml through data binding
    }

    private fun getItem(position: Int): CountriesData {
        return arrayList[position]
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyViewHolder(var binding: AdapterAsyncTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: CountriesData) {
            binding.apply {
                dataModel = model
            }
        }

    }
}