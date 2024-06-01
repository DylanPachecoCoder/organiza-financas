package com.example.organizafinancas.ui.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.organizafinancas.databinding.ItemCategoryDetailBinding
import com.example.organizafinancas.domain.model.Category

class CategoryAdapter(
    private var categoryList: List<Category> = mutableListOf(),
    private val onItemClicked: (category: Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemCategoryDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(categoryList: List<Category>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCategoryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            with(binding) {
               textviewCategoryName.text = category.name
                card.setOnClickListener {
                    onItemClicked(category)
                }
            }
        }
    }
}