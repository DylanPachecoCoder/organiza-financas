package com.example.organizafinancas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.organizafinancas.databinding.ItemFilterOptionBinding

class FilterAdapter(
    private val filterOptions: MutableList<String> = mutableListOf()
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFilterOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = filterOptions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = filterOptions[position]
        holder.bind(option)
    }

    inner class ViewHolder(private val binding: ItemFilterOptionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(option: String) {
            with(binding){
                checkboxFilterOption.text = option
            }
        }

    }
}
