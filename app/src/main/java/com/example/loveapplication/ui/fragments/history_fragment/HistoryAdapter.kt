package com.example.loveapplication.ui.fragments.history_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.loveapplication.data.local.HistoryEntity
import com.example.loveapplication.databinding.ItemHistoryBinding
import com.example.loveapplication.interfaces.OnClick


class HistoryAdapter(private val onLongClickItem: OnClick) :
    ListAdapter<HistoryEntity, HistoryAdapter.ViewHolder>(DiffCallBack()) {

    class ViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistoryEntity) {
            binding.tvFirstName.text = item.firstName
            binding.tvSecondName.text = item.secondName
            binding.tvPercentage.text = item.percentage + "%"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnLongClickListener {
            onLongClickItem.onLongClick(holder.itemView.context, getItem(position))
            true
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<HistoryEntity>() {
        override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
            return oldItem == newItem
        }
    }
}
