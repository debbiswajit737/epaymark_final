package com.epaymark.epay.adapter.reportAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.R
import com.epaymark.epay.data.model.ReportModel
import com.epaymark.epay.data.model.ReportPropertyModel
import com.epaymark.epay.databinding.ReportLayoutItemBinding
import com.epaymark.epay.utils.`interface`.CallBack


class ReportAdapter(
    val reportPropertyModel: ReportPropertyModel,
    private val items: List<ReportModel>,

    val callBack: CallBack
) : RecyclerView.Adapter<ReportAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        /*val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_items, parent, false)
        return MyViewHolder(view)*/
       /* val binding = BannerItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)*/


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ReportLayoutItemBinding =
            ReportLayoutItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)


    }




    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position],position)
    }

    override fun getItemCount(): Int {
        return items.size
    }




    inner class MyViewHolder(val binding: ReportLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReportModel, position: Int) {
            binding.model=item
            binding.propertyModel=reportPropertyModel
            binding.executePendingBindings()

        }
    }


}