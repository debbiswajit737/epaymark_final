package com.epaymark.epay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.data.model.BottomSheetStateListModel
import com.epaymark.epay.databinding.BottomStateListBinding
import com.epaymark.epay.utils.`interface`.CallBack

class BottomStateListAdapter(private val operatorList: ArrayList<BottomSheetStateListModel>, val listner: CallBack) : RecyclerView.Adapter<BottomStateListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BottomStateListBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(operatorList[position], position)
    }

    override fun getItemCount(): Int {
        return operatorList.size
    }


    inner class MyViewHolder(val binding: BottomStateListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BottomSheetStateListModel, position: Int) {
            binding.model=item
            ///*if (position!=items.size-1) {

            //}*/
            /*item.title.let {
              //  binding.tvState.text = it
            }*/

            binding.clHeader.setOnClickListener{
                item.statename.let {
                    listner.getValue(it)
                }

            }
            binding.executePendingBindings()
        }
    }


}