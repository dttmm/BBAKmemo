package com.dttmm.bbakmemo.database

import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.RoomDatabase
import com.dttmm.bbakmemo.dao.MemoDao
import com.dttmm.bbakmemo.dto.MemoDto

@Database(entities = [MemoDto::class], version = 1)
abstract class MemoDatabase: RoomDatabase() {
    companion object {
        const val TABLE = "memo"
        const val DATABASE = "memo-database.db"
    }

    abstract fun getDao(): MemoDao
}