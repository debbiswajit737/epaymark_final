package com.epaymark.epay.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.data.model.AdminBankListModel
import com.epaymark.epay.databinding.AdminBankListLayoutBinding
import com.epaymark.epay.utils.`interface`.CallBack4
import java.util.*
import kotlin.collections.ArrayList

class AdminBankListAdapter(
    private var items: List<AdminBankListModel>,
    private val callback: CallBack4
) : RecyclerView.Adapter<AdminBankListAdapter.MyViewHolder>(), Filterable {

    private var filteredList: List<AdminBankListModel> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: AdminBankListLayoutBinding =
            AdminBankListLayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(filteredList[position], position)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    inner class MyViewHolder(val binding: AdminBankListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AdminBankListModel, position: Int) {
            item.bankName?.let { bankName ->
                binding.tvBankName.text = bankName
                binding.llContainer.setOnClickListener {
                    callback.getValue4(bankName, "", "", "")
                }
            }
            item.bankLogo?.let { bankLogo ->
                binding.imageView7.setImageResource(bankLogo)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString().toLowerCase(Locale.getDefault())
                filteredList = if (charString.isEmpty()) {
                    items
                } else {
                    val filtered: MutableList<AdminBankListModel> = ArrayList()
                    for (item in items) {
                        if (item.bankName?.toLowerCase(Locale.getDefault())?.contains(charString) == true) {
                            filtered.add(item)
                        }
                    }
                    filtered
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredList = filterResults.values as List<AdminBankListModel>
                notifyDataSetChanged()
            }
        }
    }
}