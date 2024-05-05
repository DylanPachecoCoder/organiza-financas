package com.example.organizafinancas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.organizafinancas.databinding.ItemPeriodFilterBinding
import com.example.organizafinancas.domain.model.Period

class PeriodFilterAdapter(
    private val periodList: List<Period> = mutableListOf(),
    private val onItemClick: () -> Unit
) : RecyclerView.Adapter<PeriodFilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemPeriodFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = periodList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(periodList[position])
    }

    inner class ViewHolder(private val binding: ItemPeriodFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(period: Period) {
            with(binding) {
                textviewPeriodName.text = period.name
                textviewPeriodFromDate.text = period.from
                textviewPeriodToDate.text = period.to
                imagebuttonCalendar.setOnClickListener { onItemClick() }
            }
        }
    }
}