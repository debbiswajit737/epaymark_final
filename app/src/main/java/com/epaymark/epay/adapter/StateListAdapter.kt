package com.epaymark.epay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.databinding.StateListBinding
import com.epaymark.epay.utils.`interface`.CallBack
import android.widget.Filter
import android.widget.Filterable

class StateListAdapter(private var items: List<String>, private val callBack: CallBack) :
    RecyclerView.Adapter<StateListAdapter.MyViewHolder>(), Filterable {

    private var filteredItems: List<String> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: StateListBinding = StateListBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(filteredItems[position])
    }

    override fun getItemCount(): Int {
        return filteredItems.size
    }

    inner class MyViewHolder(val binding: StateListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvState.text = item
            binding.tvState.setOnClickListener {
                callBack.getValue(item)
            }
        }
    }

    // Implement filtering
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charSequence = constraint.toString().trim().toLowerCase()
                filteredItems = if (charSequence.isEmpty()) {
                    items
                } else {
                    items.filter { item ->
                        item.toLowerCase().contains(charSequence)
                    }
                }
                val results = FilterResults()
                results.values = filteredItems
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                filteredItems = results.values as List<String>
                notifyDataSetChanged()
            }
        }
    }

    // Update the list of items
    fun updateData(newItems: List<String>) {
        items = newItems
        filteredItems = newItems
        notifyDataSetChanged()
    }
}