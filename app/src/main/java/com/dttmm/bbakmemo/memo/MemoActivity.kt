package com.dttmm.bbakmemo.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dttmm.bbakmemo.R
import com.dttmm.bbakmemo.adapter.MemoAdapter
import com.dttmm.bbakmemo.databinding.ActivityMemoBinding
import com.dttmm.bbakmemo.dto.MemoDto
import com.dttmm.bbakmemo.repository.MemoRepository
import com.dttmm.bbakmemo.viewmodel.MemoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MemoActivity___"

class MemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoBinding
    private lateinit var adapter: MemoAdapter
    private val viewModel by viewModels<MemoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_memo)

        viewModel.data.observe(this) {
            adapter.data = it
            adapter.notifyDataSetChanged()
        }

        initAdapter()

        binding.fabRegister.setOnClickListener {
            Intent(this@MemoActivity, MemoEditActivity::class.java).apply {
                startActivity(this)
            }
        }
    }


    private fun initAdapter() {
        adapter = MemoAdapter()
        binding.recyclerview.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        }
        adapter.data = viewModel.data.value ?: mutableListOf()
        adapter.setOnItemClickListener = object : MemoAdapter.SetOnItemClickListener {
            override fun onClick(view: View, position: Int) {
                Intent(this@MemoActivity, MemoEditActivity::class.java).apply {
                    putExtra("id", adapter.data[position].id)
                    startActivity(this)
                }
            }
        }
    }
}