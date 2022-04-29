package com.dttmm.bbakmemo.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.dttmm.bbakmemo.database.MemoDatabase.Companion.TABLE
import com.dttmm.bbakmemo.dto.MemoDto

@Dao
interface MemoDao {

    // 전체 메모 조회
    @Query("SELECT * FROM $TABLE")
    fun selectAll(): LiveData<MutableList<MemoDto>>

    // id로 메모 조회
    @Query("SELECT * FROM $TABLE WHERE id = (:id)")
    suspend fun select(id: Int): MemoDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memo: MemoDto)

    @Update
    suspend fun update(memo: MemoDto)

    @Delete
    suspend fun delete(memo: MemoDto)
}