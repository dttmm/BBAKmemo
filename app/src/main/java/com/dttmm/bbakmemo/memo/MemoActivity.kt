package com.dttmm.bbakmemo.memo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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
                val data = adapter.data[position]

                if (!data.password.isEmpty()) {  // 비밀메모인 경우
                    val builder = AlertDialog.Builder(this@MemoActivity)
                    builder.setTitle("비밀번호를 입력하세요")
                    builder.setIcon(R.drawable.ic_baseline_lock_24)

                    val view = layoutInflater.inflate(R.layout.dialog_password, null)
                    builder.setView(view)

                    val listener = DialogInterface.OnClickListener { dialog, i ->
                        val alert = dialog as AlertDialog
                        val et = alert.findViewById<EditText>(R.id.et_password_dialog)

                        if (et?.text.toString().equals(data.password)) {
                            Intent(this@MemoActivity, MemoEditActivity::class.java).apply {
                                putExtra("id", data.id)
                                startActivity(this)
                            }
                        } else {
                            Toast.makeText(this@MemoActivity, "비밀번호가 틀렸습니다", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    builder.setPositiveButton("확인", listener)
                    builder.setNegativeButton("취소", null)
                    builder.show()

                } else {
                    Intent(this@MemoActivity, MemoEditActivity::class.java).apply {
                        putExtra("id", data.id)
                        startActivity(this)
                    }
                }
            }
        }
    }
}