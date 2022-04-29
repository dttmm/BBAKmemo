package com.dttmm.bbakmemo.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.dttmm.bbakmemo.R
import com.dttmm.bbakmemo.databinding.ActivityMemoEditBinding
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
}