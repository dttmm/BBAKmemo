package com.dttmm.bbakmemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dttmm.bbakmemo.dto.MemoDto
import com.dttmm.bbakmemo.repository.MemoRepository

class MemoViewModel : ViewModel() {
    var data:LiveData<MutableList<MemoDto>> = MemoRepository.getInstance().selectAll()
}