package com.dttmm.bbakmemo.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dttmm.bbakmemo.R
import com.dttmm.bbakmemo.databinding.ActivityMemoEditBinding
import com.dttmm.bbakmemo.dto.MemoDto
import com.dttmm.bbakmemo.repository.MemoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoEditBinding
    private var id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_memo_edit)

        binding.btnSave.setOnClickListener {
            saveMemo()
        }

        binding.btnDelete.setOnClickListener {
            deleteMemo()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        id = intent.getIntExtra("id", -1)
        if (id >= 0) {
            CoroutineScope(Dispatchers.IO).launch {
                val memo = MemoRepository.getInstance().select(id)
                binding.memo = memo
            }
        } else {
            binding.btnDelete.visibility = View.GONE
            binding.etContent.requestFocus()
        }
    }

    private fun saveMemo() {
        if (check()) {
            if (id >= 0) {
                CoroutineScope(Dispatchers.IO).launch {
                    MemoRepository.getInstance().update(binding.memo as MemoDto)
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    MemoRepository.getInstance().insert(
                        MemoDto(
                            content = binding.etContent.text.toString(),
                            password = binding.etPassword.text.toString()
                        )
                    )
                }
            }
            finish()
        }
    }

    private fun deleteMemo() {
        CoroutineScope(Dispatchers.IO).launch {
            MemoRepository.getInstance().delete(binding.memo as MemoDto)
        }
        finish()
    }

    private fun check(): Boolean {
        if (binding.etContent.text.isNullOrBlank()) {
            Toast.makeText(this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show()
            return false
        } else return true
    }
}