package com.epaymark.epay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.data.model.ReceiptModel
import com.epaymark.epay.databinding.ReceiptLayoutBinding


class ReceiptAdapter(private val items: ArrayList<ReceiptModel>) : RecyclerView.Adapter<ReceiptAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //receipt_layout


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ReceiptLayoutBinding = ReceiptLayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position],position)
    }

    override fun getItemCount(): Int {
        return items.size
    }




    inner class MyViewHolder(val binding: ReceiptLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReceiptModel, position: Int) {
            binding.model=item
            binding.executePendingBindings()
        }
    }


}