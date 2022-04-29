package com.dttmm.bbakmemo

import android.app.Application
import com.dttmm.bbakmemo.repository.MemoRepository

class MemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MemoRepository.init(this)
    }
}