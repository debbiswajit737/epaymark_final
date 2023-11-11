package com.epaymark.epay.adapter

import android.R
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.data.model.ListIcon
import com.epaymark.epay.databinding.RechargeLayoutBinding
import com.epaymark.epay.utils.*
import com.epaymark.epay.utils.`interface`.CallBack


class MostPopularAdapter(private val items: List<ListIcon>, val circleShape: Int, val listner: CallBack) : RecyclerView.Adapter<MostPopularAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        /*val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_items, parent, false)
        return MyViewHolder(view)*/
       /* val binding = BannerItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)*/


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RechargeLayoutBinding = RechargeLayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position],position)
    }

    override fun getItemCount(): Int {
        return items.size
    }




    inner class MyViewHolder(val binding: RechargeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListIcon, position: Int) {
           // if (position!=items.size-1) {

                binding.imgIcon.setBackgroundResource(circleShape)
            /*}
            else{

                val tintColor = ContextCompat.getColor(
                    binding.imgIcon.context,
                    R.color.black
                )

                binding.imgIcon.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN)
            }*/
            item.title?.let { title->
                binding.tvTitle.text = title
                binding.llItem.setOnClickListener{
                    listner.getValue(title)
                }
            }
            item.image?.let {image->
                binding.imgIcon.setImageResource(image)
            }




        }
    }


}