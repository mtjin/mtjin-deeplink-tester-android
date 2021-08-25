package com.mtjin.mtjindlt.views.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.mtjindlt.R
import com.mtjin.mtjindlt.data.main.DeepLinkUrl
import com.mtjin.mtjindlt.databinding.ItemMainHistoryBinding

class MainHistoryAdapter(
    private val linkClick: (DeepLinkUrl) -> Unit,
    private val deleteClick: (DeepLinkUrl) -> Unit
) : ListAdapter<DeepLinkUrl, MainHistoryAdapter.ViewHolder>(
    diffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMainHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_main_history,
            parent,
            false
        )
        return ViewHolder(binding).apply {
            binding.tvUrl.setOnClickListener { view ->
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                linkClick(getItem(position))
            }
            binding.ivDelete.setOnClickListener { view ->
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                deleteClick(getItem(position))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)) //변경된 점 -> getItem(position) 메서드가 생겼다.
    }

    class ViewHolder(private val binding: ItemMainHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DeepLinkUrl) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DeepLinkUrl>() {
            override fun areContentsTheSame(oldItem: DeepLinkUrl, newItem: DeepLinkUrl) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: DeepLinkUrl, newItem: DeepLinkUrl) =
                oldItem.url == newItem.url
        }
    }
}