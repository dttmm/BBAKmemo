package com.dttmm.bbakmemo.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.dttmm.bbakmemo.database.MemoDatabase
import com.dttmm.bbakmemo.database.MemoDatabase.Companion.DATABASE
import com.dttmm.bbakmemo.dto.MemoDto

class MemoRepository private constructor(context: Context) {
    companion object {
        private var INSTANCE: MemoRepository? = null

        fun init(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = MemoRepository(context)
            }
        }

        fun getInstance(): MemoRepository {
            return INSTANCE ?: throw IllegalStateException("MemoRepository is not initialized")
        }
    }

    private val database = Room.databaseBuilder(
        context.applicationContext,
        MemoDatabase::class.java,
        DATABASE
    ).build()

    private val dao = database.getDao()

    fun selectAll(): LiveData<MutableList<MemoDto>> {
        return dao.selectAll()
    }

    suspend fun select(id: Int): MutableList<MemoDto> {
        return dao.select(id)
    }

    suspend fun insert(memo: MemoDto) {
        dao.insert(memo)
    }

    suspend fun update(memo: MemoDto) {
        dao.update(memo)
    }

    suspend fun delete(memo: MemoDto) {
        dao.delete(memo)
    }
}