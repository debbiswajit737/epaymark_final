package com.epaymark.epay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.data.model.ListIcon
import com.epaymark.epay.databinding.MyBig9LayoutBinding
import com.epaymark.epay.utils.`interface`.CallBack


class MyBig9Adapter(private val items: List<ListIcon>, val circleShape: Int,val callBack:  CallBack) : RecyclerView.Adapter<MyBig9Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        /*val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_items, parent, false)
        return MyViewHolder(view)*/
       /* val binding = BannerItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)*/


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MyBig9LayoutBinding = MyBig9LayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position],position)
    }

    override fun getItemCount(): Int {
        return items.size
    }




    inner class MyViewHolder(val binding: MyBig9LayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListIcon, position: Int) {
            ///*if (position!=items.size-1) {
                binding.imgIcon.setBackgroundResource(circleShape)
            //}*/
            item.title?.let {title->
                binding.tvTitle.text = title
                binding.llContainer.setOnClickListener{
                    callBack.getValue(title)
                }
            }
            item.image?.let {image->
                binding.imgIcon.setImageResource(image)
            }


        }
    }


}