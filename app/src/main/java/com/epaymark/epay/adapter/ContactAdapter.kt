package com.epaymark.epay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.data.model.ContactModel
import com.epaymark.epay.databinding.ReportContactItemLayoutBinding


class ContactAdapter(private val items: ArrayList<ContactModel>) : RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ReportContactItemLayoutBinding = ReportContactItemLayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position],position)
    }

    override fun getItemCount(): Int {
        return items.size
    }




    inner class MyViewHolder(val binding: ReportContactItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ContactModel, position: Int) {

            item.property.let {
                binding.tvProperty.text = it
            }
           item.reportValue.let {
                binding.tvPropertyValue.text = it
            }
            item.btnValue.let {
                binding.tvBtn.text = it
            }
        }
    }
}