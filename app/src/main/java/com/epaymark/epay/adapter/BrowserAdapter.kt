package com.epaymark.epay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.data.model.BrowserModel
import com.epaymark.epay.databinding.BrowserListBinding
import com.epaymark.epay.utils.`interface`.CallBack

class BrowserAdapter(private val operatorList: ArrayList<BrowserModel>, val listner: CallBack) : RecyclerView.Adapter<BrowserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BrowserListBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(operatorList[position], position)
    }

    override fun getItemCount(): Int {
        return operatorList.size
    }


    inner class MyViewHolder(val binding: BrowserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BrowserModel, position: Int) {
            //binding.model=item
            ///*if (position!=items.size-1) {

            //}*/
           /* item.title.let {
              //  binding.tvState.text = it
            }

            binding.clHeader.setOnClickListener{
                item.title.let {
                    listner.getValue(it)
                }

            }*/
            //binding.executePendingBindings()
        }
    }


}