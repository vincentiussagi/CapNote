package com.example.nocap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nocap.data.model.Note
import com.example.nocap.databinding.TopicItemBinding

class MainAdapter(val onClick: (Note) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private val dataset = mutableListOf<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            TopicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.createView(position)
    }

    override fun getItemCount(): Int = dataset.size

    fun updateDataset(list: List<Note>) {
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val itemBinding: TopicItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun createView(position: Int) {
            itemBinding.tvItem.text = dataset[position].title
            itemView.setOnClickListener { onClick(dataset[position]) }
        }
    }
}