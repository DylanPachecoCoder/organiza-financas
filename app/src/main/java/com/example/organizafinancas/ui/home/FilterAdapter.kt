package com.example.organizafinancas.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.organizafinancas.databinding.ItemFilterOptionBinding
import com.example.organizafinancas.domain.model.SelectableFilter

class FilterAdapter(
    private val filterOptions: List<SelectableFilter>,
    private val onItemClicked: () -> Unit
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemFilterOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = filterOptions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = filterOptions[position]
        holder.bind(option, onItemClicked)
    }

    inner class ViewHolder(private val binding: ItemFilterOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(option: SelectableFilter, onItemClicked: () -> Unit) {
            with(binding.checkboxFilterOption) {
                text = option.name
                isChecked = option.isSelected
                setOnCheckedChangeListener { _, isChecked ->
                    option.isSelected = isChecked
                    onItemClicked()
                }
            }
        }
    }
}
