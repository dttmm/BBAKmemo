package com.dttmm.bbakmemo.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dttmm.bbakmemo.databinding.ListMemoItemBinding
import com.dttmm.bbakmemo.dto.MemoDto

private const val TAG = "MemoAdapter___"

class MemoAdapter(initData: MutableList<MemoDto>) : RecyclerView.Adapter<MemoAdapter.ViewHolder>(), Filterable {

    var data = mutableListOf<MemoDto>()
    var searchData = mutableListOf<MemoDto>()
    lateinit var setOnItemClickListener: SetOnItemClickListener

    inner class ViewHolder(val binding: ListMemoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(memo: MemoDto) {
            if (!memo.password.isEmpty()) {    // 비밀번호 설정이 되어 있는 경우
                memo.content = "비밀메모입니다"
                itemView.setBackgroundColor(Color.GRAY)
            } else {
                itemView.setBackgroundColor(Color.WHITE)
            }
            binding.memo = memo
        }
    }

    init {
        data = initData
        searchData = initData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListMemoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val memo = searchData[position]
        holder.bindInfo(memo)
        holder.itemView.setOnClickListener {
            setOnItemClickListener.onClick(holder.itemView, position)
        }
    }

    override fun getItemCount(): Int {
        return searchData.size
    }

    interface SetOnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    //필터를 위한 코드
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    searchData = data
                } else {
                    //이부분에서 원하는 데이터를 검색할 수 있음
                    val filteredList = data.filter { it.content.contains(charString) }
                    searchData = filteredList as MutableList<MemoDto>
                }
                val filterResults = FilterResults()
                filterResults.values = searchData
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                searchData = filterResults.values as MutableList<MemoDto>
                notifyDataSetChanged()
            }
        }
    }
}