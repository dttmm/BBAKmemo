package com.dttmm.bbakmemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dttmm.bbakmemo.databinding.ListMemoItemBinding
import com.dttmm.bbakmemo.dto.MemoDto

class MemoAdapter : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    var data = mutableListOf<MemoDto>()
    lateinit var setOnItemClickListener: SetOnItemClickListener

    inner class ViewHolder(val binding: ListMemoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(memo: MemoDto) {
            binding.memo = memo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListMemoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val memo = data[position]
        holder.bindInfo(memo)
        holder.itemView.setOnClickListener {
            setOnItemClickListener.onClick(holder.itemView, position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface SetOnItemClickListener {
        fun onClick(view: View, position: Int)
    }
}