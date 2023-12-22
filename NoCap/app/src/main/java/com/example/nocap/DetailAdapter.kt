package com.example.nocap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nocap.data.model.NoteDetail
import com.example.nocap.databinding.ContentItemBinding

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    private val dataset = mutableListOf<NoteDetail>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ContentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        return holder.createView(position)
    }

    override fun getItemCount(): Int = dataset.size

    fun getDataset(): MutableList<NoteDetail> {
        return dataset
    }
    fun updateDataset(list: List<NoteDetail>){
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }

    fun addDataset(noteDetail:NoteDetail){
        dataset.add(noteDetail)
        notifyDataSetChanged()
    }

    inner class DetailViewHolder(private val itemBinding: ContentItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun createView(position: Int) {
            itemBinding.tvItem.text = dataset[position].text
        }
    }
}