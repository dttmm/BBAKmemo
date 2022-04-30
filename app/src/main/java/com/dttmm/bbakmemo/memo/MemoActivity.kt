package com.dttmm.bbakmemo.memo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
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
            adapter.searchData = it
            adapter.notifyDataSetChanged()
        }

        initAdapter()

        binding.fabRegister.setOnClickListener {
            Intent(this@MemoActivity, MemoEditActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.toolbar.setTitle(R.string.app_name)
        setSupportActionBar(binding.toolbar)
    }


    private fun initAdapter() {
        adapter = MemoAdapter(viewModel.data.value ?: mutableListOf())
        binding.recyclerview.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            it.addItemDecoration(
                DividerItemDecoration(
                    this@MemoActivity,
                    DividerItemDecoration.VERTICAL
                )
            );
        }

        adapter.setOnItemClickListener = object : MemoAdapter.SetOnItemClickListener {
            override fun onClick(view: View, position: Int) {
                val data = adapter.searchData[position]

                if (!data.password.isEmpty()) {  // 비밀메모인 경우
                    val builder = AlertDialog.Builder(this@MemoActivity)
                    builder.setTitle("\uD83D\uDD25빡알림\uD83D\uDD25")
                    builder.setMessage("비밀번호를 입력하세요")

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val searchItem = menu!!.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        // menuItem 에서 searchView 가져온다음 리스너 달면 됨
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return true
    }
}