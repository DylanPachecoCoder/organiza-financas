package com.example.organizafinancas.ui.periodfilter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.organizafinancas.commons.extensions.ONE
import com.example.organizafinancas.commons.extensions.format
import com.example.organizafinancas.databinding.ItemPeriodFilterBinding
import com.example.organizafinancas.domain.model.PaymentFilter

class PeriodFilterAdapter(
    private val paymentFilterList: List<PaymentFilter> = mutableListOf(),
    private val onItemClick: (PaymentFilter, Int) -> Unit
) : RecyclerView.Adapter<PeriodFilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemPeriodFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = paymentFilterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isLast = position == itemCount - Int.ONE
        holder.bind(paymentFilterList[position], isLast, position)
    }

    inner class ViewHolder(private val binding: ItemPeriodFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(paymentFilter: PaymentFilter, isLast: Boolean, position: Int) {
            with(binding) {
                textviewPeriodName.text = paymentFilter.type.paymentType
                textviewPeriodFromDate.text = paymentFilter.initialDate.format()
                textviewPeriodToDate.text = paymentFilter.finishDate.format()
                divider.isVisible = isLast.not()
                imagebuttonCalendar.setOnClickListener {
                    onItemClick(paymentFilter, position)
                }
            }
        }
    }
}